package io.ssafy.mallook.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Schema(description = "회원 정보 저장시 요청 DTO")
public record MemberDetailReq(
        @Schema(name="닉네임")
        @NotBlank(message = "닉네임은 공백일 수 없습니다.")
        @Size(min = 2, max = 16, message = "닉네임의 길이는 2~16자로 제한됩니다.")
        String nickname,
        @Schema(name="성별")
        @NotBlank(message = "성별은 공백일 수 없습니다.")
        String gender,
        @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$")
        @Schema(name="생년월일")
        @NotBlank(message = "생일은 공백일 수 없습니다.")
        String birth,
        @Schema(name="전화번호")
        @NotBlank(message = "전화번호는 공백일 수 없습니다.")
        String phone,
        @Schema(name="시도")
        @NotBlank(message = "지역정보('시도')는 공백일 수 없습니다.")
        String city,
        @Schema(name = "시군구")
        @NotBlank(message = "지역정보('시군구')는 공백일 수 없습니다.")
        String district,
        @Schema(name = "상세주소")
        @NotBlank(message = "지역정보('상세주소')는 공백일 수 없습니다.")
        String address,
        @Schema(name = "우편번호")
        @NotBlank(message = "지역정보('우편번호')는 공백일 수 없습니다.")
        String zipcode
) {
}
