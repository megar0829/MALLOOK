package io.ssafy.mallook.domain.heart.application;

import io.ssafy.mallook.domain.heart.dao.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartServiceImpl implements HeartService{

    private final HeartRepository heartRepository;
}
