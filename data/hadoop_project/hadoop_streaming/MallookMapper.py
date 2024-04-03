#!/usr/bin/env python3
import io
import sys
import json
import re
from collections import Counter
from pykospacing import Spacing
from soynlp.word import WordExtractor
from soynlp.tokenizer import LTokenizer

# 표준 입력의 인코딩을 UTF-8로 설정
sys.stdin = io.TextIOWrapper(sys.stdin.buffer, encoding='utf-8')

# 키워드 관련 초기화
spacing = Spacing()     # PyKoSpacing 인스턴스 생성
word_extractor = WordExtractor()    # WordExtractor 인스턴스 생성
word_scores = word_extractor.word_scores()  # word scores 계산
tokenizer = LTokenizer(scores=word_scores)  # 토크나이저 생성

# 키워드 및 불용어 셋 로드 (경로는 상황에 맞게 조정)
keyword_file = "keyword.txt"
stopword_file = "stopword.txt"

with open(keyword_file, 'r', encoding='utf-8') as file:
    keyword_data = [line.strip() for line in file]

with open(stopword_file, 'r', encoding='utf-8') as file:
    stopword_data = [line.strip() for line in file]

def review_preprocessing(corpus):
    keywords = {}

    # 전처리 및 토큰화
    corpus = re.sub(r'[^가-힣]+', ' ', corpus)
    corpus = spacing(corpus)
    tokens = tokenizer.tokenize(corpus)

    for token in tokens:
        # 불용어 제거
        if token in stopword_data:
            continue
        
        for keyword in keyword_data:
            # 키워드 포함 여부 확인
            if keyword in token:
                keyword_count = keywords.setdefault(keyword, 0) + 1
                keywords[keyword] = keyword_count
                break
    
    # counter 객체 생성
    counter = Counter(keywords)

    # 빈도수가 높은 5개의 키워드 추출
    keywords_top5 = [key for key, _ in counter.most_common(5)]

    return list(keywords.keys()), keywords_top5

for line in sys.stdin:
    try:
        # JSON 로딩
        data = json.loads(line)
        product_id = data.get("product_id")
        corpus = data.get("oneline", "")
        # 리뷰 전처리
        keywords, keywords_top5 = review_preprocessing(corpus)
        
        # 결과 출력
        result = {"product_id": product_id, "keywords": keywords, "keywords_top5": keywords_top5}
        print(json.dumps(result, ensure_ascii=False))  # 인코딩을 지정하지 않음
    except json.JSONDecodeError as e:
        # JSON 디코딩 오류 처리
        continue
