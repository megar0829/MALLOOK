package io.ssafy.mallook.domain.member.application;

import io.ssafy.mallook.domain.member.dto.request.MemberDetailReq;
import io.ssafy.mallook.domain.member.dto.response.MemberDetailRes;

import java.text.ParseException;
import java.util.UUID;

public interface MemberService {
   MemberDetailRes findMemberDetail(UUID memberId);
   String makeRandomNickname();
   boolean validateNickname(String nickname);
   void saveMemberDetail(UUID memberId, MemberDetailReq memberDetailReq) throws ParseException;
   void updateNickname(UUID memberId, String nickname);
}
