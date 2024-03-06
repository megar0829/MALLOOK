package io.ssafy.mallook.domain.heart.application;

import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface HeartService {

    Page<ScriptListDto> getLikeScriptList(UUID id, Pageable pageable);

    void likeScript(UUID id, LikeDto likeDto);

    void likeStyle(UUID id, LikeDto likeDto);

    void unlikeScript(UUID id, LikeDto likeDto);

    void unlikeStyle(UUID id, LikeDto likeDto);
}
