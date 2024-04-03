from dotenv import load_dotenv
import pymongo
import os

load_dotenv()
password = os.getenv("MONGODB_PASSWORD")
client = pymongo.MongoClient(f"mongodb+srv://root:{password}@cluster0.stojj99.mongodb.net/?retryWrites=true&w=majority&appName=Cluster")

db = client["products"]
collection = db["products"]

query = {
    # "mall_name": "hiver",
    # "gender": "unisex",
    "main_category": "가방",
    "sub_category": "토트백",
    "keywords": {"$exists": True},
}

results = collection.find(query)

for result in results:
    print(result['url'], result['_id'])