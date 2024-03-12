package io.ssafy.mallook.domain.member.api;


import io.ssafy.mallook.domain.member.application.MemberService;
import io.ssafy.mallook.domain.member.dto.request.MemberDetailReq;
import io.ssafy.mallook.domain.member.dto.request.MemberNicknameReq;
import io.ssafy.mallook.domain.member.dto.response.MemberDetailRes;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "멤버", description = "Member 관련 API")
@Log4j2
public class MemberController {

    private final MemberService memberService;
    @Operation(summary = "회원 정보 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "회원 정보 조회 실패")
            })
    @GetMapping
    public ResponseEntity<BaseResponse<MemberDetailRes>> findMemberDetail(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO){
        var result = memberService.findMemberDetail(userSecurityDTO.getId());
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result
        );
    }
    @Operation(summary = "닉네임 중복검사",
            responses = {
                    @ApiResponse(responseCode = "200", description = "닉네임 중복검사 성공"),
                    @ApiResponse(responseCode = "404", description = "닉네임 중복검사 실패")
            })
    @GetMapping("/nickname")
    public ResponseEntity<BaseResponse<String>> validateNickname(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @RequestBody MemberNicknameReq nicknameReq
            ){
        var result = memberService.validateNickname(nicknameReq.nickname());
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result? "중복된 닉네임은 허용되지 않습니다.": "사용할 수 있는 닉네임입니다."
        );
    }
    @Operation(summary = "랜덤 닉네임 생성",
            responses = {
                    @ApiResponse(responseCode = "200", description = "닉네임 중복검사 성공"),
                    @ApiResponse(responseCode = "404", description = "닉네임 중복검사 실패")
            })
    @GetMapping("/random")
    public ResponseEntity<BaseResponse<String>> getRandomNickname(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO
    ){
        var result = memberService.makeRandomNickname();
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result
        );
    }
    @Operation(
            summary = "회원 정보 저장",
            responses = {
                    @ApiResponse(responseCode = "200", description = "추가 정보 저장 성공"),
                    @ApiResponse(responseCode = "404", description = "추가 정보 저장 실패")
                    }
    )
    @PostMapping
    public ResponseEntity<BaseResponse<String>> saveMemberDetail(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @Valid @RequestBody MemberDetailReq memberDetailReq) throws ParseException {
        memberService.saveMemberDetail(userSecurityDTO.getId(), memberDetailReq );
        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "추가 정보 저장 성공"
        );
    }

    @Operation(summary = "닉네임 변경",
            responses = {
                    @ApiResponse(responseCode = "200", description = "닉네임 변경 성공"),
                    @ApiResponse(responseCode = "404", description = "닉네임 변경 실패")
            })
    @PatchMapping("/nickname")
    public ResponseEntity<BaseResponse<String>> updateNickname(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @Valid @NotNull @Size(min = 2, max = 16) @RequestBody String nickname) {
        memberService.updateNickname(userSecurityDTO.getId(), nickname);
        return BaseResponse.success(
                SuccessCode.UPDATE_SUCCESS,
                "닉네임 변경 성공"
        );
    }

}
