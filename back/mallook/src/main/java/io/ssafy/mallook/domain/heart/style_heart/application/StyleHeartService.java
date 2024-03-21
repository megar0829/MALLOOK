package io.ssafy.mallook.domain.heart.style_heart.application;

import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface StyleHeartService {

    Slice<StyleListRes> getLikeStyleList(Long cursor, UUID id, Pageable pageable);

    void likeStyle(UUID id, LikeDto likeDto);

    void unlikeStyle(UUID id, LikeDto likeDto);

    Long findMaxHeartId();
}
