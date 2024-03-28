import pymongo
from dotenv import load_dotenv
import os
from konlpy.tag import Kkma
from konlpy.tag import Okt
import re
from pykospacing import Spacing

def review_preprocessing(reviews):
    keywords = []

    tokenizer = Okt()       # Okt 형태소 분석기
    spacing = Spacing()     # PyKoSpacing 패키지

    for review in reviews:
        # 숫자, 영어 알파벳, 특수 문자, 구두점 및 개행 문자, 줄바꿈, 초성 제거
        cleaned_review = re.sub(r'[a-zA-Z\s\W_]+|[^ㄱ-ㅎㅏ-ㅣ가-힣]+|\n+', '', review)

        # 띄어쓰기
        cleaned_review = spacing(cleaned_review)  
        print(cleaned_review)

        # 형태소 분석을 통해 명사만 추출
        tokens = tokenizer.morphs(cleaned_review)
        
        # 키워드를 고유한 토큰으로 업데이트
        keywords += tokens
        
    return keywords


load_dotenv()
password = os.getenv("MONGODB_PASSWORD")

# MongoDB 연결 설정
client = pymongo.MongoClient(f"mongodb+srv://root:{password}@cluster0.stojj99.mongodb.net/?retryWrites=true&w=majority&appName=Cluster")
db = client["products"]
collection = db["products"]

cursor = collection.find()

for document in cursor:
    reviews = set()

  # 리뷰 없는 상품은 건너뜀
    if not document['reviews']['reviews']:
        continue

    for review in document['reviews']['reviews'][:100]:
        # content가 존재하는 리뷰에 대해서만 수행
        if not review.get('contents', False):
            continue
            
        reviews.add(review['contents'])

    if not reviews:
        continue
    
    keywords = review_preprocessing(reviews)
    print(keywords)
    break

  # 추출된 키워드 저장
  # document['test_keywords'] = keywords
  # collection.update_one({"_id": document["_id"]}, {"$set": document})