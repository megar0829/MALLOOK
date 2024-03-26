package io.ssafy.mallook.domain.keyword.application;

import io.ssafy.mallook.domain.keyword.dto.response.KeywordListRes;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.entity.Script;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KeywordServiceImpl implements KeywordService {

    private final ScriptRepository scriptRepository;

    @Override
    public List<KeywordListRes> getKeywordList() {
        List<Script> top50Script = scriptRepository.findTop50ScriptsOrderByTotalLikeDesc();
        List<String> keywordList = new ArrayList<>(top50Script.stream()
                .flatMap(script -> script.getKeywordList().stream())
                .toList());
        Collections.shuffle(keywordList);
        return keywordList.stream()
                .map(KeywordListRes::toDto)
                .limit(8).toList();
    }
}
