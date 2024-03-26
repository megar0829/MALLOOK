package io.ssafy.mallook.domain.member.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.dto.request.MemberDetailReq;
import io.ssafy.mallook.domain.member.dto.response.MemberDetailRes;
import io.ssafy.mallook.domain.member.entity.Address;
import io.ssafy.mallook.domain.member.entity.Gender;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.member.entity.MemberRole;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import io.ssafy.mallook.global.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public MemberDetailRes findMemberDetail(UUID memberId) {
        var memberDetail = memberRepository.findById(memberId)
                .orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new MemberDetailRes(memberDetail.getNickname(),
                sdf.format(memberDetail.getBirth()),
                memberDetail.getGender().toString(),
                memberDetail.getPhone(),
                memberDetail.getPoint(),
                memberDetail.getExp(),
                memberDetail.getAddress().getCity(),
                memberDetail.getAddress().getDistrict(),
                memberDetail.getAddress().getAddress(),
                memberDetail.getAddress().getZipcode());
    }

    @Override
    public String makeRandomNickname() {
        var test = RandomStringUtils.random(6, true, true);
        System.out.println(test);
        return "램덤닉네임" + Integer.toString ((int) (Math.random()*10000));
    }


    @Override
    public boolean validateNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }





    @Override
    @Transactional
    public void saveMemberDetail(UUID memberId, MemberDetailReq memberDetailReq) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Member member = Member.builder()
                        .id(memberId)
                        .nickname(memberDetailReq.nickname())
                        .gender(Gender.valueOf(Gender.class, memberDetailReq.gender()))
                        .birth(sdf.parse(memberDetailReq.birth()))
                        .phone(memberDetailReq.phone())
                        .point(0L)
                        .exp(0L)
                        .address(new Address(memberDetailReq.city(),
                                memberDetailReq.district(),
                                memberDetailReq.address(),
                                memberDetailReq.zipcode()))
                            .build();

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
                .orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        member.changeNickname(nickname);
        memberRepository.save(member);
    }



}
