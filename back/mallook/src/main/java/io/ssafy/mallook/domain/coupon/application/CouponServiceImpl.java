package io.ssafy.mallook.domain.coupon.application;

import io.ssafy.mallook.domain.coupon.dao.CouponRepository;
import io.ssafy.mallook.domain.coupon.dto.response.CouponRes;
import io.ssafy.mallook.domain.coupon.dto.response.MemberCouponRes;
import io.ssafy.mallook.domain.coupon.entity.Coupon;
import io.ssafy.mallook.domain.coupon.entity.CouponType;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.member_coupon.dao.MemberCouponRepository;
import io.ssafy.mallook.domain.member_coupon.entity.MemberCoupon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final MemberCouponRepository memberCouponRepository;
    private final RedissonClient redissonClient;
    public static final Integer couponStock = 100;  //todo: redis에 있는 숫자로 판단? 아니면 따로
    public static final Integer outOfStock = 0;
    public static final String PURCHASED_COUPON_SET_KEY = "PURCHASED_COUPON_SET_KEY";

    @Override
    public Slice<CouponRes> findCouponListFirst(Pageable pageable, UUID memberId) {
        Long maxId = couponRepository.getMaxId();
        if (Objects.isNull(maxId)) {
            return new SliceImpl<>(List.of(), pageable, false);
        }
//        return couponRepository.findCouponBy(pageable, maxId);
        return null;
    }

    @Override
    public Slice<CouponRes> findCouponList(Pageable pageable, Long cursor, UUID memberId) {
//        return couponRepository.findCouponBy(pageable, cursor);
        return null;
    }

    @Override
    public Slice<MemberCouponRes> findMyCouponListFirst(Pageable pageable, UUID memberId) {
        Long maxId = memberCouponRepository.getMaxId(memberId);
        if (Objects.isNull(maxId)) {
            return new SliceImpl<>(List.of(), pageable, false);
        }
        return couponRepository.findAllByMemberId(pageable, memberId, maxId + 1);
    }

    @Override
    public Slice<MemberCouponRes> findMyCouponList(Pageable pageable, UUID memberId, Long cursor) {
        return couponRepository.findAllByMemberId(pageable, memberId, cursor + 1);
    }

    @Override
    @Transactional
    public void saveNewCoupon() {
        // 이벤트 쿠폰 디비에 저장
        Coupon coupon = Coupon.builder()
                .type(CouponType.RATIO)
                .name("선착순 쿠폰")
                .expiredTime(LocalDateTime.now().plusYears(1))
                .amount(20L)
                .stock(couponStock)
                .build();
        couponRepository.save(coupon);
        // couponId랑 stock을 redis에 저장해줘야할듯
    }

    public Integer availableCoupons(String key) {
        var stock = redissonClient.getBucket(key).get();
        if (Objects.isNull(stock)) {
            return 0;
        }
        return (int)stock;

    }

    public void setCouponStock(String key, Integer quantity) {
        redissonClient.getBucket(key).set(quantity);
    }

    // 이미 구매한 사용자인지 확인
    private boolean userDuplicate(UUID memberId) {
        return redissonClient.getSet(PURCHASED_COUPON_SET_KEY).contains(memberId.toString());
    }

    @Override
    @Transactional
    public void decreaseCoupon(Long couponId, UUID memberId) {
        // 같은 쿠폰이 재고보다 많이 발급되지 않도록
        // 한 사람 당 한번만 등록 가능
        boolean usingLock;
        Long waitTime = 1L;
        Long leaseTime = 3L;
        String keyName = "couponKey" + couponId.toString();
        final RLock lock = redissonClient.getLock(keyName);
        final String thread = Thread.currentThread().getName();
        try {
            // trylock을 기준으로 구독 시작
            usingLock = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
            // lock 이 걸려있다면 return
            if (!usingLock) {
                return;
            }
            if (userDuplicate(memberId)) {
                return;
            }
            // 재고 소진이라면 return
            final int currentStock = availableCoupons(keyName);
            if (currentStock > outOfStock) {
                log.info("{} - 쿠폰 모두 소진 (수량 : {}개)", thread, currentStock);
                return;
            }
            log.info("쿠폰 발급 중 : {} - 현재 잔여 쿠폰 {} (수량 : {}개)", thread, keyName, currentStock);
            setCouponStock(keyName, currentStock - 1);
            memberCouponRepository.save(MemberCoupon.builder()
                    .coupon(Coupon.builder().id(couponId).build())
                    .member(Member.builder().id(memberId).build())
                    .build());
            redissonClient.getSet(PURCHASED_COUPON_SET_KEY).add(memberId.toString());
        } catch (InterruptedException e) {
            // thread가 인터럽트 된 경우
            Thread.currentThread().interrupt();
        } finally {
            // 락 해제
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }
}
