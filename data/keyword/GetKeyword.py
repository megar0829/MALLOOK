import os
import re
import pymongo
from dotenv import load_dotenv
from pykospacing import Spacing
from soynlp.word import WordExtractor
from soynlp.tokenizer import LTokenizer
from soynlp.noun import LRNounExtractor

# PyKoSpacing 인스터스 생성
spacing = Spacing()         

# WordExtractor 인스턴스 생성
word_extractor = WordExtractor()

# 토크나이저 생성
word_scores = word_extractor.word_scores()
tokenizer = LTokenizer(scores=word_scores)


# 불용어 셋
current_directory = os.path.dirname(os.path.abspath(__file__))
stopwords_file = os.path.join(current_directory, "stopword.txt")

with open(stopwords_file, 'r', encoding='utf-8') as file:
    stopwords = [line.strip() for line in file]


def review_preprocessing(corpus):
    # 전처리
    corpus = re.sub(r'[^가-힣]+', ' ', corpus)
    corpus = spacing(corpus)

    # 토큰화
    tokens = tokenizer.tokenize(corpus)

    # 불용어 셋
    

    for token in tokens:
        # 한 글자 제거
        if len(token) == 1:
            continue
        
        # 불용어 제거
        if token in stopwords:
            continue
        
        # 키워드 셋에 포함되어 있는지 확인하고 카운트

    return tokens


load_dotenv()
password = os.getenv("MONGODB_PASSWORD")

# MongoDB 연결 설정
client = pymongo.MongoClient(f"mongodb+srv://root:{password}@cluster0.stojj99.mongodb.net/?retryWrites=true&w=majority&appName=Cluster")
db = client["products"]
collection = db["products"]

cursor = collection.find()

i = 0
for document in cursor:
    i += 1
    reviews = ''

    # 리뷰 없는 상품은 건너뜀
    if not document['reviews']['reviews']:
        continue

    for review in document['reviews']['reviews'][:100]:
        # content가 존재하는 리뷰에 대해서만 수행
        if not review.get('contents', False):
            continue
            
        reviews += f" {review['contents']}"

    if not reviews:
        continue
    
    keywords = review_preprocessing(reviews)
    print(keywords)

    if i == 4:
        break

  # 추출된 키워드 저장
  # document['test_keywords'] = keywords
  # collection.update_one({"_id": document["_id"]}, {"$set": document})
