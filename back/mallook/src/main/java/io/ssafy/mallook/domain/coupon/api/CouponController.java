package io.ssafy.mallook.domain.coupon.api;

import io.ssafy.mallook.domain.coupon.application.CouponService;
import io.ssafy.mallook.domain.coupon.dto.response.CouponRes;
import io.ssafy.mallook.domain.member.dto.response.MemberDetailRes;
import io.ssafy.mallook.domain.member_coupon.application.MemberCouponService;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
@Log4j2
public class CouponController {
    private final CouponService couponService;
    private final MemberCouponService memberCouponService;
    @GetMapping
    public ResponseEntity<BaseResponse<List<CouponRes>>> findMyCouponList(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO){
        var result = couponService.findMyCouponList(userSecurityDTO.getId());
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result
        );
    }
    @PostMapping
    public ResponseEntity<BaseResponse<String>> saveMyCoupon(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @RequestBody Long couponId){
        memberCouponService.saveMyCoupon(userSecurityDTO.getId(), couponId);
        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "쿠폰 등록 완료"
        );
    }
    @DeleteMapping
    public ResponseEntity<BaseResponse<String>> deleteMyCoupon(
            @RequestBody Long memberCouponId){
        memberCouponService.deleteMyCoupon(memberCouponId);
        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "쿠폰 삭제 완료"
        );
    }

}
