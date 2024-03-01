package io.ssafy.mallook.domain.script.application;

import io.ssafy.mallook.domain.script.dto.request.ScriptCreatDto;

import java.util.UUID;

public interface ScriptService {

    void createScript(ScriptCreatDto scriptCreateDto, UUID id);
}
