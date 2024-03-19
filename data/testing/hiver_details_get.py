# hiver 상품 상세정보 크롤링(API)
import requests
from fake_useragent import UserAgent
import json

# 중복을 제거하기 위한 파일 열기
with open('hiver_used.json', 'r') as hiver_used:
    # 중복을 제거하기 위해 세트로 저장
    used = set(json.load(hiver_used))

# 카테고리별로 정렬된 상품id 조회
with open('hiver_productIds.json', 'r', encoding='utf-8') as hiver_producIds:
    productIds = json.load(hiver_producIds)

hiver_details = {}

def save_detail():
    pass
