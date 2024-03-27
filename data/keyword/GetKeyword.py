import pymongo
from dotenv import load_dotenv
import os
from konlpy.tag import Kkma       # 한글 자연어 처리
from konlpy.tag import Okt
import re


kkma = Kkma()
okt = Okt()


def review_preprocessing(reviews):
    keywords = set()
    for review in reviews:
        print(review)
    
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
        # content가 존재하는 리뷰에 대해서만 수행
        if not review.get('content', False):
            continue

        reviews.add(review['content'])
    
    if not reviews:
        continue

    keywords = review_preprocessing(reviews)
    break


  # 추출된 키워드 저장
  # document['keywords'] = keywords
  # collection.update_one({"_id": document["_id"]}, {"$set": document}) 