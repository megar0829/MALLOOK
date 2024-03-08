package io.ssafy.mallook.domain.style.application;

import io.ssafy.mallook.domain.style.dto.request.StyleInsertReq;
import io.ssafy.mallook.domain.style.dto.response.StyleDetailRes;
import io.ssafy.mallook.domain.style.dto.response.StylePageRes;
import org.springframework.data.domain.Pageable;

import java.util.UUID;
public interface StyleService {
    StylePageRes findStyleList(Pageable pageable);
    StyleDetailRes findStyleDetail(Long id);
    void saveStyle(UUID memberId, StyleInsertReq styleInsertRes);
    void DeleteStyle(UUID memberId, Long styleId);
}
