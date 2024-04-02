from dotenv import load_dotenv
import pymongo
import os
from bson.objectid import ObjectId

load_dotenv()
password = os.getenv("MONGODB_PASSWORD")
client = pymongo.MongoClient(f"mongodb+srv://root:{password}@cluster0.stojj99.mongodb.net/?retryWrites=true&w=majority&appName=Cluster")

db = client["products"]
collection = db["products"]

crop_list = os.listdir('sample/sample_image/after')
num = 0
for crop_file in crop_list:
    num += 1
    # 파일 이름에서 _id와 확장자를 추출
    crop_id, extension = os.path.splitext(crop_file)
    
    # 해당 _id를 가진 문서 조회
    document = collection.find_one({"_id": ObjectId(crop_id)})
    
    # 이미 'crop' 필드가 있는지 확인
    if document and 'crop' not in document:
        # 'crop' 필드가 없으면 업데이트 수행
        result = collection.update_one({"_id": ObjectId(crop_id)}, {"$set": {"crop": f'https://zooting-s3-bucket.s3.ap-northeast-2.amazonaws.com/mallook/{crop_id}.PNG'}})
        
        if result.modified_count == 1:
            print(f"crop 추가 성공: {num, crop_file}")
        else:
            print(f"crop 추가 실패: {num, crop_file}")
    elif document:
        # 이미 'crop' 필드가 있으면 중복 메시지 출력
        print(f"crop 이미 존재: {num, crop_file}")
    else:
        # 해당 _id를 가진 문서가 없으면 메시지 출력
        print(f"문서가 존재하지 않음: {num, crop_id}")