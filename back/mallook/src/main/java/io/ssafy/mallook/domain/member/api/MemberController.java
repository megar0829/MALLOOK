package io.ssafy.mallook.domain.member.api;


import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.member.application.MemberService;
import io.ssafy.mallook.domain.member.dto.request.MemberAdditionalInfoReq;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;
    @PostMapping
    public ResponseEntity<BaseResponse<String>> saveMemberAdditionalInfo(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @RequestBody MemberAdditionalInfoReq memberAdditionalInfoReq) throws ParseException {
        memberService.saveMemberAdditionalInfo(userSecurityDTO.getId(), memberAdditionalInfoReq );
        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "추가 정보 저장 성공"
        );
    }
}
