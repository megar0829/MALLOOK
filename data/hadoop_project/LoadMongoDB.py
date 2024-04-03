from dotenv import load_dotenv
import pymongo
import os
import json

load_dotenv()
password = os.getenv("MONGODB_PASSWORD")
client = pymongo.MongoClient(f"mongodb+srv://root:{password}@cluster0.stojj99.mongodb.net/?retryWrites=true&w=majority&appName=Cluster")

db = client["hiver"]
collection = db["hiver"]

documents = collection.find()

# JSON 파일에 저장하기
with open('Documents.json', 'w', encoding='utf-8') as file:
    for document in documents:
        # 'reviews'의 'contents'를 모두 합쳐서 'oneline' 변수에 저장
        if document['reviews']['reviews']:
            oneline = ''

            for reviews in document['reviews']['reviews']:
                oneline += f"{reviews['contents']} "
            
            # 'oneline'과 'product_id' 필드만 포함된 새로운 딕셔너리 생성
            data_to_save = {
                'product_id': document['product_id'],
                'oneline': oneline
            }
        
            # JSON 형식으로 저장
            json.dump(data_to_save, file, ensure_ascii=False)
            file.write('\n')