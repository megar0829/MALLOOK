package io.ssafy.mallook.util;

import io.ssafy.mallook.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.misc.UUIDRandomizer;

public class MemberFixtureFactory {

    public static Member createMember() {
        EasyRandomParameters parameter = new EasyRandomParameters()
                .randomize(FieldPredicates.named("id"), new UUIDRandomizer()); // id 필드를 랜덤한 UUID 값으로 설정
        EasyRandom easyRandom = new EasyRandom(parameter);
        return new EasyRandom(parameter).nextObject(Member.class);
    }
}
