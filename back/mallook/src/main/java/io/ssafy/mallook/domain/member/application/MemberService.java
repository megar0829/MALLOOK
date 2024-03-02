package io.ssafy.mallook.domain.member.application;

import io.ssafy.mallook.domain.member.dto.request.MemberAdditionalInfoReq;

import java.text.ParseException;
import java.util.UUID;

public interface MemberService {
   void saveMemberAdditionalInfo(UUID memberId, MemberAdditionalInfoReq memberAdditionalInfoReq) throws ParseException;
}
