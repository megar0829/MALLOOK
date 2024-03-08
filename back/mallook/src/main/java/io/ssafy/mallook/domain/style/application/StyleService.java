package io.ssafy.mallook.domain.style.application;

import io.ssafy.mallook.domain.style.dto.request.StyleInsertReq;
import io.ssafy.mallook.domain.style.dto.response.StyleDetailRes;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import io.ssafy.mallook.domain.style.dto.response.StylePageRes;
import io.ssafy.mallook.domain.style.entity.Style;
import org.springframework.data.domain.Pageable;

import java.util.UUID;
import java.util.List;
public interface StyleService {
    void saveStyle(UUID memberId, StyleInsertReq styleInsertRes);
    StylePageRes findStyleList(Pageable pageable);
    StyleDetailRes findStyleDetail(Long id);
    void DeleteStyle(UUID memberId, Long styleId);
}
