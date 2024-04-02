import os
import pymongo
from dotenv import load_dotenv


load_dotenv()
password = os.getenv("MONGODB_PASSWORD")

# MongoDB 연결 설정
client = pymongo.MongoClient(f"mongodb+srv://root:{password}@cluster0.stojj99.mongodb.net/?retryWrites=true&w=majority&appName=Cluster")
db = client["products"]
collection = db["products"]

# # 조건에 맞는 문서 가져오기
# documents = collection.find({"mall_name": "29cm"})

# # 각 문서에 대해 userSize를 user_size로 변경
# for document in documents:
#     for reviews in document['reviews']['reviews']:
#         if 'userSize' in reviews:
#             reviews['user_size'] = reviews.pop('userSize')
#     # 수정된 데이터로 업데이트
#     collection.update_one({'_id': document['_id']}, {'$set': {'reviews': document['reviews']}})


# 집계(aggregation)를 사용하여 'sub_category' 필드의 고유한 값 조회
pipeline = [
    {"$group": {"_id": "$sub_category"}}
]

unique_sub_categories = collection.aggregate(pipeline)

# 고유한 sub_category 값을 담을 세트(set) 생성
unique_sub_category_set = set()

# 조회된 결과를 세트에 추가
for sub_category in unique_sub_categories:
    unique_sub_category_set.add(sub_category['_id'])

# 세트에 있는 값들 출력
print("고유한 sub_category 목록:")
for sub_category in unique_sub_category_set:
    print(sub_category)