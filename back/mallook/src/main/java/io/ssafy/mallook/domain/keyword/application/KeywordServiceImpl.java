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
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KeywordServiceImpl implements KeywordService {

    private final ScriptRepository scriptRepository;

    @Override
    public List<KeywordListRes> getKeywordList() {
        List<Script> top50Script = scriptRepository.findTop50ScriptsOrderByTotalLikeDesc();

        // 키워드 리스트에서 중복을 제거하기 위해 Set 사용
        Set<String> uniqueKeywords = top50Script.stream()
                .flatMap(script -> script.getKeywordList().stream())
                .collect(toSet()); // Set으로 수집하여 중복 제거

        List<String> shuffledKeywords = new ArrayList<>(uniqueKeywords);
        Collections.shuffle(shuffledKeywords);

        return shuffledKeywords.stream()
                .map(KeywordListRes::toDto)
                .limit(8)
                .collect(toList());
    }
}
