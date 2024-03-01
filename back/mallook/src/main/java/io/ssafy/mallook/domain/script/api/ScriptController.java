package io.ssafy.mallook.domain.script.api;

import io.ssafy.mallook.domain.script.application.ScriptService;
import io.ssafy.mallook.domain.script.dto.request.ScriptCreatDto;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scripts")
@Slf4j
public class ScriptController {

    private final ScriptService scriptService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createScript(@AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
                             @RequestBody @Valid ScriptCreatDto scriptCreateDto) {
        log.info("userPk: " + userSecurityDTO.getId().toString());
        UUID id = userSecurityDTO.getId();
        scriptService.createScript(scriptCreateDto, id);
    }
}
