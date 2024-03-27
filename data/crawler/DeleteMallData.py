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
query = { 'mall_name': mall_name }
result = collection.delete_many(query)
print(result.deleted_count, "개의 문서가 삭제되었습니다.")