package io.ssafy.mallook.domain.heart.application;

import io.ssafy.mallook.domain.heart.dto.request.LikeDto;

import java.util.UUID;

public interface HeartService {
    void likeScript(UUID id, LikeDto likeDto);
}
