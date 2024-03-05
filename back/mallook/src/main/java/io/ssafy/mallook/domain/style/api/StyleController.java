package io.ssafy.mallook.domain.style.api;

import io.ssafy.mallook.domain.style.dao.StyleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/styles")
@RequiredArgsConstructor
@Log4j2
public class StyleController {
    private final StyleRepository styleRepository;
}
