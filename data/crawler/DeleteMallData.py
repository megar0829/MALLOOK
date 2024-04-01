from dotenv import load_dotenv
import pymongo
import os


print("삭제할 데이터의 쇼핑몰 이름을 입력하세요.")
mall_name = input()
load_dotenv()
password = os.getenv("MONGODB_PASSWORD")
client = pymongo.MongoClient(f"mongodb+srv://root:{password}@cluster0.stojj99.mongodb.net/?retryWrites=true&w=majority&appName=Cluster")

db = client["products"]
collection = db["products"]
# query = { 'mall_name': mall_name }
# result = collection.delete_many(query)
# print(result.deleted_count, "개의 문서가 삭제되었습니다.")

# 조건에 맞는 문서 가져오기
documents = collection.find({"sub_category": "코트(분류필요)"})

# 각 문서에 대해 userSize를 user_size로 변경
for document in documents:
    # for reviews in document['reviews']['reviews']:
    #     if 'userSize' in reviews:
    if '숏' in document['name']:
        document['sub_category'] = "숏코트"
    else:
        document['sub_category'] = '롱코트'
    # 수정된 데이터로 업데이트
    collection.update_one({'_id': document['_id']}, {'$set': document})