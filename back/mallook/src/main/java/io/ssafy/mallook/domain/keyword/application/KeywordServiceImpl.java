package io.ssafy.mallook.domain.keyword.application;

import io.ssafy.mallook.domain.keyword.dao.KeywordRepository;
import io.ssafy.mallook.domain.keyword.dto.response.KeywordListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KeywordServiceImpl implements KeywordService{

    private final KeywordRepository keywordRepository;

    @Override
    public List<KeywordListRes> getKeywordList() {
        List<KeywordListRes> list = new ArrayList<>();
        return list;
    }
}
