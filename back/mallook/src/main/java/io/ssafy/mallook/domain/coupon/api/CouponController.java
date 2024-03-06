package io.ssafy.mallook.domain.coupon.api;

import io.ssafy.mallook.domain.coupon.application.CouponService;
import io.ssafy.mallook.domain.coupon.dto.response.CouponPageRes;
import io.ssafy.mallook.domain.coupon.dto.response.CouponRes;
import io.ssafy.mallook.domain.member_coupon.application.MemberCouponService;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
@Log4j2
public class CouponController {
    private final CouponService couponService;
    private final MemberCouponService memberCouponService;
    @Operation(summary = "내 쿠폰 리스트 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "쿠폰 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "쿠폰 조회 실패")
            })
    @GetMapping
    public ResponseEntity<BaseResponse<CouponPageRes>> findMyCouponList(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @PageableDefault(size= 20, sort="id", direction = Sort.Direction.DESC, page=0) Pageable pageable){
        var result = couponService.findMyCouponList(pageable, userSecurityDTO.getId());
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result
        );
    }
    @Operation(summary = "내 쿠폰 등록",
            responses = {
                    @ApiResponse(responseCode = "200", description = "쿠폰 등록 성공"),
                    @ApiResponse(responseCode = "404", description = "쿠폰 등록 실패")
            })
    @PostMapping
    public ResponseEntity<BaseResponse<String>> saveMyCoupon(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @Valid @NotNull @RequestBody Long couponId){
        memberCouponService.saveMyCoupon(userSecurityDTO.getId(), couponId);
        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "쿠폰 등록 완료"
        );
    }
    @Operation(summary = "내 쿠폰 삭제",
            responses = {
                    @ApiResponse(responseCode = "200", description = "쿠폰 삭제 성공"),
                    @ApiResponse(responseCode = "404", description = "쿠폰 삭제 실패")
            })
    @DeleteMapping
    @PreAuthorize("@authService.authorizeToDeleteMemberCoupon(#userSecurityDTO.getId(), #memberCouponId)")
    public ResponseEntity<BaseResponse<String>> deleteMyCoupon(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @Valid @NotNull @RequestBody Long memberCouponId){
        memberCouponService.deleteMyCoupon(memberCouponId);
        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "쿠폰 삭제 완료"
        );
    }

}
