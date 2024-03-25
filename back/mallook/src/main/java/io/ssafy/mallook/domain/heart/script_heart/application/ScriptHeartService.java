package io.ssafy.mallook.domain.heart.script_heart.application;

import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface ScriptHeartService {

    Slice<ScriptListDto> getLikeScriptList(Long cursor, UUID id, Pageable pageable);

    void likeScript(UUID id, LikeDto likeDto);

    void unlikeScript(UUID id, LikeDto likeDto);

    Long findMaxHeartId();
}
