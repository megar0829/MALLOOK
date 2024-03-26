package io.ssafy.mallook.domain.keyword.application;

import io.ssafy.mallook.domain.keyword.dto.response.KeywordListRes;

import java.util.List;
import java.util.UUID;

public interface KeywordService {
    List<KeywordListRes> getKeywordList();
}
