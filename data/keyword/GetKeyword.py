import os
import re
import pymongo
from dotenv import load_dotenv
from collections import Counter
from pykospacing import Spacing
from soynlp.word import WordExtractor
from soynlp.tokenizer import LTokenizer
from konlpy.tag import Kkma


kkma = Kkma()   # Kkma 인스터스 생성
spacing = Spacing()     # PyKoSpacing 인스턴스 생성
word_extractor = WordExtractor()    # WordExtractor 인스턴스 생성

# 토크나이저 생성
word_scores = word_extractor.word_scores()
tokenizer = LTokenizer(scores=word_scores)

# 현재 폴더 위치
current_directory = os.path.dirname(os.path.abspath(__file__))

# 불용어 셋
stopword_file = os.path.join(current_directory, "stopword.txt")
with open(stopword_file, 'r', encoding='utf-8') as file:
    stopword_data = [line.strip() for line in file]

# 키워드 셋
keyword_file = os.path.join(current_directory, "keyword.txt")
with open(keyword_file, 'r', encoding='utf-8') as file:
    keyword_data = [line.strip() for line in file]


def compare_word_meaning(word1, word2):
    # 두 단어의 형태소 분석 결과 비교
    morphs1 = kkma.morphs(word1)
    morphs2 = kkma.morphs(word2)

    if morphs1[0] == morphs2[0]:
        return True
    else:
        return False


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
            # 키워드 셋에 있는 단어와 유사성 판별 (토큰 생성하는 데 개당 3초 내외로 걸림)
            # if compare_word_meaning(morphs1, morphs2):

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


load_dotenv()
password = os.getenv("MONGODB_PASSWORD")

# MongoDB 연결 설정
client = pymongo.MongoClient(f"mongodb+srv://root:{password}@cluster0.stojj99.mongodb.net/?retryWrites=true&w=majority&appName=Cluster")
db = client["products"]
collection = db["products"]

cursor = collection.find()

for document in cursor:
    if document.get('keywords', 0):
        continue

    reviews = ''

    # 리뷰 없는 상품은 건너뜀
    if not document['reviews']['reviews']:
        continue

    for review in document['reviews']['reviews'][:100]:
        # content가 존재하는 리뷰에 대해서만 수행
        if not review.get('contents', False):
            continue
            
        reviews += f" {review['contents']}"

    if reviews:
        keywords, keywords_top5 = review_preprocessing(reviews)

    # 추출된 키워드 저장
    document['keywords'] = keywords
    document['keywords_top5'] = keywords_top5
    
    try:
        collection.update_one({"_id": document["_id"]}, {"$set": document})
        print("상품 정보가 MongoDB에 성공적으로 저장되었습니다!")
    except Exception as e:
            print(f"MongoDB에 저장하는 중 오류가 발생했습니다: {e}")