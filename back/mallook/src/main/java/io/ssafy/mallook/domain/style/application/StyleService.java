package io.ssafy.mallook.domain.style.application;

import io.ssafy.mallook.domain.style.dto.request.StyleInsertReq;
import io.ssafy.mallook.domain.style.dto.response.StyleDetailRes;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import io.ssafy.mallook.domain.style.entity.Style;

import java.util.UUID;
import java.util.List;
public interface StyleService {
    void saveStyle(UUID memberId, StyleInsertReq styleInsertRes);
    List<StyleListRes> findStyleList();
    StyleDetailRes findStyleDetail(Long id);
}
