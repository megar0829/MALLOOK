package io.ssafy.mallook.domain.heart.application;

import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface HeartService {

    Slice<ScriptListDto> getLikeScriptList(Long cursor, UUID id, Pageable pageable);

    Page<StyleListRes> getLikeStyleList(UUID id, Pageable pageable);

    void likeScript(UUID id, LikeDto likeDto);

    void likeStyle(UUID id, LikeDto likeDto);

    void unlikeScript(UUID id, LikeDto likeDto);

    void unlikeStyle(UUID id, LikeDto likeDto);
}
