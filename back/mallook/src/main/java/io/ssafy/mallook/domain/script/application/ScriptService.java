package io.ssafy.mallook.domain.script.application;

import io.ssafy.mallook.domain.product.dto.response.ProductsPageRes;
import io.ssafy.mallook.domain.script.dto.request.ScriptCreatDto;
import io.ssafy.mallook.domain.script.dto.request.ScriptDeleteListDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptDetailDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.UUID;

public interface ScriptService {

    Long getMaxScriptId();

    Slice<ScriptListDto> getScriptList(Long cursor, UUID id, Pageable pageable);

    Slice<ScriptListDto> getScriptList(Long cursor, Pageable pageable);

    List<ScriptProductDto> getRecommendProductById(Long scriptId, Pageable pageable);

    ProductsPageRes getRecommendProductDetail(Long scriptId, String cursor, Pageable pageable);

    ScriptDetailDto getScriptDetail(Long scriptId);

    ScriptDetailDto getScriptDetail(UUID memberId,Long scriptId);

    void createScript(ScriptCreatDto scriptCreateDto, UUID id);

    void deleteScript(ScriptDeleteListDto scriptDeleteListDto);
}
