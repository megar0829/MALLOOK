# hiver 크롤링(API)
import requests

# 상품 상세정보 관련
def detail():
    productId =   40000001
    prodcutId2 = 142872929
    hiverDetail = 'https://www.hiver.co.kr/_next/data/NkOT5yhyYbV_Xxvt8xp9e/ko/products/115619185.json?productId={pruductId}'