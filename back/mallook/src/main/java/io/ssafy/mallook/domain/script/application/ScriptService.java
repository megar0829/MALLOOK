package io.ssafy.mallook.domain.script.application;

import io.ssafy.mallook.domain.script.dto.request.ScriptCreatDto;
import io.ssafy.mallook.domain.script.dto.request.ScriptDeleteListDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptDetailDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface ScriptService {

    Slice<ScriptListDto> getScriptList(Long cursor, UUID id, Pageable pageable);

    ScriptDetailDto getScriptDetail(Long scriptId);

    void createScript(ScriptCreatDto scriptCreateDto, UUID id);

    void deleteScript(ScriptDeleteListDto scriptDeleteListDto);
}
