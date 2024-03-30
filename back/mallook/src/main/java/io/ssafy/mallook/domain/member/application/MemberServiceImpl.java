package io.ssafy.mallook.domain.member.application;

import io.ssafy.mallook.domain.cart.dao.CartRepository;
import io.ssafy.mallook.domain.coupon.dao.CouponRepository;
import io.ssafy.mallook.domain.grade.entity.Grade;
import io.ssafy.mallook.domain.grade.entity.Level;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.dto.request.MemberDetailReq;
import io.ssafy.mallook.domain.member.dto.response.MemberDetailRes;
import io.ssafy.mallook.domain.member.dto.response.NicknameRes;
import io.ssafy.mallook.domain.member.entity.Address;
import io.ssafy.mallook.domain.member.entity.Gender;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.member.entity.MemberRole;
import io.ssafy.mallook.domain.orders.dao.OrderRepository;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    public static final List<String> nicknameAdjective = List.of("예쁜", "화난", "귀여운", "배고픈", "철학적인", "현학적인",
            "슬픈", "푸른", "비싼", "밝은");
    public static final List<String> nicknameNoun = List.of("호랑이", "비버", "강아지", "부엉이", "여우", "치타",
            "문어", "고양이", "미어캣", "다람쥐");

    @Override
    public MemberDetailRes findMemberDetail(UUID memberId) {
        var memberDetail = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        var cart = cartRepository.findMyCartByMember(memberDetail).orElse(null);
        return MemberDetailRes.builder()
                .nickname(memberDetail.getNickname())
                .nicknameTag((memberDetail.getNicknameTag()))
                .birth(Objects.nonNull(memberDetail.getBirth()) ? sdf.format(memberDetail.getBirth()) : null)
                .gender(Objects.nonNull(memberDetail.getGender()) ? memberDetail.getGender().toString() : null)
                .phone(memberDetail.getPhone())
                .address(memberDetail.getAddress())
                .point(memberDetail.getPoint())
                .exp(memberDetail.getExp())
                .grade(Objects.nonNull(memberDetail.getGrade()) ? memberDetail.getGrade().toString() : null)
                .expRange(Objects.nonNull(memberDetail.getGrade()) ? memberDetail.getGrade().getGradeRange() : List.of())
                .cartProduct(Objects.nonNull(cart) ? cart.getCartProductList().size() : 0L)
                .memberCoupon(Objects.nonNull(memberDetail.getMyCouponList()) ? memberDetail.getMyCouponList().size() : 0L)
                .coupon(couponRepository.countBy())
                .orders(orderRepository.countByMember(memberDetail)) // null 체크?
                .build();
    }

    @Override
    public NicknameRes makeRandomNickname() {
        String randomName = nicknameAdjective.get((int) (Math.random() * nicknameAdjective.size()))
                + nicknameNoun.get((int) (Math.random() * nicknameNoun.size()));
        return new NicknameRes(randomName);
    }


    @Override
    @Transactional
    public void saveMemberDetail(UUID memberId, MemberDetailReq memberDetailReq) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        String randomTag = RandomStringUtils.random(6, true, true);
        while (memberRepository.existsByNicknameTag(randomTag)) {
            randomTag = RandomStringUtils.random(6, true, true);
        }
        try {
            member.setNickname(memberDetailReq.nickname());
            member.setNicknameTag(randomTag);
            member.setGender(Gender.valueOf(Gender.class, memberDetailReq.gender()));
            member.setBirth(sdf.parse(memberDetailReq.birth()));
            member.setAddress(Address.builder()
                                    .city(memberDetailReq.city())
                                    .district(memberDetailReq.district())
                                    .address(memberDetailReq.address())
                                    .zipcode(memberDetailReq.zipcode()).build());;
            member.setPhone(memberDetailReq.phone());
            member.setExp(0L);
            member.setPoint(0L);
            member.setGrade(Grade.builder()
                            .member(Member.builder().id(memberId).build())
                            .level(Level.LEVEL1)
                            .build());
            // 최초 권한만 가진 유저에게 추가 권한 부여
            member.getRole().remove(MemberRole.BASIC_USER);
            memberRepository.save(member);
        } catch (ParseException e) {
            throw new BaseExceptionHandler(ErrorCode.INVALID_TYPE_VALUE);
        }
    }


    @Override
    @Transactional
    public void updateNickname(UUID memberId, String nickname) {
        var member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        member.changeNickname(nickname);
        memberRepository.save(member);
    }

}
