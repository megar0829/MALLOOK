package io.ssafy.mallook.global.security.dto;

import io.ssafy.mallook.domain.member.entity.SocialType;

public record SocialData(
        SocialType socialType,
        String id,
        String email
) {
}
