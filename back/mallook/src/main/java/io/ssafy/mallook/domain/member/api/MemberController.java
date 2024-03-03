package io.ssafy.mallook.domain.member.api;


import io.ssafy.mallook.domain.member.application.MemberService;
import io.ssafy.mallook.domain.member.dto.request.MemberDetailReq;
import io.ssafy.mallook.domain.member.dto.response.MemberDetailRes;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;
    @PostMapping
    public ResponseEntity<BaseResponse<String>> saveMemberDetail(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @RequestBody MemberDetailReq memberDetailReq) throws ParseException {
        memberService.saveMemberDetail(userSecurityDTO.getId(), memberDetailReq );
        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "추가 정보 저장 성공"
        );
    }
    @GetMapping
    public ResponseEntity<BaseResponse<MemberDetailRes>> findMemberDetail(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO){
        var result = memberService.findMemberDetail(userSecurityDTO.getId());
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result
        );
    }
    @PatchMapping("/nickname")
    public ResponseEntity<BaseResponse<String>> updateNickname(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @RequestBody String nickname) {
        memberService.updateNickname(userSecurityDTO.getId(), nickname);
        return BaseResponse.success(
                SuccessCode.UPDATE_SUCCESS,
                "닉네임 변경 성공"
        );
    }

}
