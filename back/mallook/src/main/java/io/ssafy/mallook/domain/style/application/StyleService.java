package io.ssafy.mallook.domain.style.application;

import io.ssafy.mallook.domain.style.dto.request.StyleInsertReq;
import io.ssafy.mallook.domain.style.dto.response.StyleDetailRes;
import io.ssafy.mallook.domain.style.dto.response.StyleRes;
import io.ssafy.mallook.domain.style.dto.response.StyledWorldCupDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.UUID;
public interface StyleService {
    Slice<StyleRes> findStyleListFirst(Pageable pageable);
    Slice<StyleRes> findStyleList(Pageable pageable, Long cursor);
    List<StyledWorldCupDto> getWorldCupList();
    StyleDetailRes findStyleDetail(Long id);
    void saveStyle(UUID memberId, StyleInsertReq styleInsertRes);
    void DeleteStyle(UUID memberId, List<Long> styleIdList);
}
