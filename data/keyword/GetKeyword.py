import pymongo
from dotenv import load_dotenv
import os
from konlpy.tag import Kkma
from konlpy.tag import Okt
import re


def review_preprocessing(reviews):
    print(reviews)
    keywords =[]
    okt = Okt()  # Okt 형태소 분석기 초기화
    
    for review in reviews:
        # 숫자, 영어 알파벳, 특수 문자, 구두점 및 개행 문자 제거
        cleaned_review = re.sub(r'[0-9a-zA-Z\s\W_]+', '', review)
        
        # 공백 제거
        cleaned_review = cleaned_review.strip()
        
        # 형태소 분석을 통해 명사만 추출
        tokens = okt.nouns(cleaned_review)
        
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
    reviews = set()   # 리뷰 셋

  # 리뷰 없는 상품은 건너뜀
    if not document['reviews']['reviews']:
        continue

    for review in document['reviews']['reviews']:
        print(review)
        # content가 존재하는 리뷰에 대해서만 수행
        if not review.get('content', False):
            continue

        reviews.add(review['content'])
    
    if not reviews:
        continue
    
    keywords = review_preprocessing(reviews)
    # print(keywords)
    break


  # 추출된 키워드 저장
  # document['keywords'] = keywords
  # collection.update_one({"_id": document["_id"]}, {"$set": document})