package io.ssafy.mallook.domain.heart.api;

import io.ssafy.mallook.domain.heart.application.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;
}
