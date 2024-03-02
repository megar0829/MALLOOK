package io.ssafy.mallook.domain.member.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.dto.request.MemberAdditionalInfoReq;
import io.ssafy.mallook.domain.member.entity.Address;
import io.ssafy.mallook.domain.member.entity.Gender;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Transactional
    @Override
    public void saveMemberAdditionalInfo(UUID memberId, MemberAdditionalInfoReq memberAdditionalInfoReq) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Member member = Member.builder()
                        .id(memberId)
                        .nickname(memberAdditionalInfoReq.nickname())
                        .gender(Gender.valueOf(memberAdditionalInfoReq.gender()))
                        .birth(sdf.parse(memberAdditionalInfoReq.birth()))
                        .phone(memberAdditionalInfoReq.phone())
                        .address(new Address(memberAdditionalInfoReq.city(),
                                memberAdditionalInfoReq.district(),
                                memberAdditionalInfoReq.address(),
                                memberAdditionalInfoReq.zipcode()))
                        .build();
        memberRepository.save(member);
    }
}
