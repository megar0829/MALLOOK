package io.ssafy.mallook.global.batch;

import io.ssafy.mallook.domain.member.entity.Member;

public record MemberExpDto(
        Member member,
        Long purchaseRecord
) {
    public Long calculateExp() {
        return this.member.getPoint() + this.purchaseRecord / 10;
    }
}
