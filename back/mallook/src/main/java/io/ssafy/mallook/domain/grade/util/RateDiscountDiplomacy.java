package io.ssafy.mallook.domain.grade.util;

import io.ssafy.mallook.domain.grade.dao.GradeRepository;
import io.ssafy.mallook.domain.grade.entity.Grade;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RateDiscountDiplomacy implements DiscountDiplomacy {

    final private GradeRepository gradeRepository;

    @Override
    public Long discount(Member member) {
        Grade memberGrade = gradeRepository.findByMember(member)
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        return switch (memberGrade.getLevel()) {
            case LEVEL1, LEVEL2 -> 1L;
            case LEVEL3, LEVEL4 -> 2L;
            case LEVEL5, LEVEL6 -> 3L;
            case LEVEL7 -> 4L;
        };
    }
}
