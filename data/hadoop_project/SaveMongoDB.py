from dotenv import load_dotenv
import pymongo
import os
import json

load_dotenv()
password = os.getenv("MONGODB_PASSWORD")
client = pymongo.MongoClient(f"mongodb+srv://root:{password}@cluster0.stojj99.mongodb.net/?retryWrites=true&w=majority&appName=Cluster")

db = client["hiver"]
collection = db["hiver"]

# pyspark/PySparkPart-00000.json 파일 열기
with open("hadoop_project/pyspark/PySparkPart-00000.json", "r", encoding="utf-8") as file:
    data_00 = file.read()

# pyspark/PySparkPart-00001.json 파일 열기
with open("hadoop_project/pyspark/PySparkPart-00001.json", "r", encoding="utf-8") as file:
    data_01 = file.read()

# 데이터를 루프 돌면서 MongoDB에 업데이트
number = 0
for dataset in [data_00, data_01]:
    for data in dataset.splitlines():
        line = json.loads(data)
        product_id = line.get("product_id")
        keywords = line.get("keywords")
        keywords_top5 = line.get("keywords_top5")

        # MongoDB에서 해당 product_id를 가진 문서 찾기
        mongo_doc = collection.find_one({"product_id": product_id})

        if mongo_doc:
            number += 1
            # 문서가 존재하면 keywords 및 keywords_top5 필드 추가
            mongo_doc["keywords"] = keywords
            mongo_doc["keywords_top5"] = keywords_top5

            # 업데이트된 문서를 MongoDB에 저장
            collection.update_one({"product_id": mongo_doc["product_id"]}, {"$set": mongo_doc})

            print(f'{number}개 저장 완료')