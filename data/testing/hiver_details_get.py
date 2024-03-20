# hiver 상품 상세정보 크롤링(API)
import requests
from fake_useragent import UserAgent
import json
from bs4 import BeautifulSoup

# API 키
API_KEY = '3b17176f2eb5fdffb9bafdcc3e4bc192b013813caddccd0aad20c23ed272f076_1423639497'
# 카테고리 번호
category_numbers = {
    '상의': {
        '긴팔티': [
            426, 312, 1427,
        ],
        '반팔티': [
            425, 311, 1426,
        ],
        '민소매': [
            600, 576,
        ],
        '후드티': [
            1424,
        ],
        # 후드티, 후드집업
        '후드티(분류필요)': [
            428, 314,
        ],
        '맨투맨': [
            427, 313, 1423,
        ],
        '니트/스웨터': [
            429, 315, 1425, 1430, 1432, 1433
        ],
        '셔츠/블라우스': [
            432, 433, 434, 435, 436, 601, 437, 800,
            317, 318, 319, 320, 321, 577, 322,
            1445, 1446, 1447,
        ],
        '기타': [
            430, 316, 649,
        ],
    },
    '하의': {
        '데님': [
            441, 325, 1450,
        ],
        '면': [
            440, 324,
        ],  
        '슬랙스': [
            438, 323, 1449,
        ],
        '트레이닝/조거팬츠': [
            443, 327, 656,
        ],
        '숏팬츠': [
            442, 326, 1453,
        ],
        # 스커트, 레깅스, 기타
        '기타(분류필요)': [
            444, 328, 650,
        ], 
        # 데님, 면, 기타
        '스키니(분류필요)': [
            1451,
        ],
        # 데님, 면, 기타
        '와이드(분류필요)': [
            1452,
        ],
    },
    '아우터': {
        # 숏패딩/패딩조끼, 롱패딩
        '패딩(분류필요)': [
            1438, 1439,
        ],
        '숏패딩/패딩조끼': [
            563, 562,
        ],
        '롱패딩': [
            561, 560,
        ],
        # 숏코트, 롱코트
        '코트(분류필요)': [
            418, 306, 1441, 1442, 1443,
        ],
        '라이더재킷': [
            1440,
        ],
        '블레이저': [
            557, 556,
        ],
        '재킷': [
            422, 414, 1435, 1436,
        ],
        # 라이더 재킷, 무스탕, 플리스, 바람막이
        '재킷(분류필요)': [
            420, 308, 1437,
        ],
        # 점퍼, 무스탕, 플리스, 바람막이
        '점퍼(분류필요)': [
            419, 307,
        ],
        '가디건': [
            421, 309, 1432,
        ],
        # 플리스, 후드집업, 바람막이, 기타
        '기타(분류필요)': [
            423, 310, 648,
        ],
        # 재킷, 후드집업, 바람막이, 기타
        '베스트(분류필요)': [
            559, 558,
        ],
    },
    '가방': {
        '크로스백': [
            455, 338,
        ],
        '토트백': [
            1465,
        ],
        '클러치': [
            456, 339, 1466,
        ],
        '에코/캔버스백': [
            454, 337,
        ],
        '백팩': [
            453, 336, 1468,
        ],
        # 토트백, 웨이스트백, 기타
        '기타(분류필요)': [
            616, 617, 457, 592, 593, 340, 1467, 653, 659,
        ],
    },
    '신발': {
        '스니커즈': [
            447, 330, 1459,
        ],
        # 러닝화/워킹화, 스포츠화(축구, 농구, 테니스)
        '운동화(분류필요)': [
            446, 329, 
        ],
        '스포츠화': [
            672,
        ],
        # 구두, 로퍼
        '구두/로퍼(분류필요)': [
            448, 331,
        ],
        '구두': [
            1462,
        ],
        '로퍼': [
            1460,
        ],
        '부츠': [
            449, 333, 1461,
        ],
        # 샌들/슬리퍼, 뮬/블로퍼
        '샌들/슬리퍼': [
            450, 334, 1463,
        ],
        '기타': [
            451, 335, 651,
        ],
    },
    '모자': {
        # 볼캡/야구모자, 스냅백
        '캡(분류필요)': [
            608, 584,
        ],
        '비니': [
            609, 585,
        ],
        '버킷햇': [
            610, 586,
        ],
        # 스냅백, 베레모, 페도라, 기타
        '기타(분류필요)': [
            611, 587,
        ],
    },
    '빅사이즈': {
        # 긴팔티, 반팔티
        '티셔츠(분류필요)': [
            799,
        ],
        # 후드티, 맨투맨
        '맨투맨/후드(분류필요)': [
            1401,
        ],
        # 니트/스웨터, 가디건
        '니트/가디건(분류필요)': [
            801,
        ],
        # 상의[기타], 트레이닝/조거팬츠, 아우터[기타]
        '트레이닝복(분류필요)': [
            802,
        ],
        # 숏패딩/패딩조끼, 롱패딩, 무스탕, 점퍼, 플리스, 가디건, 기타
        '점퍼/야상/패딩(분류필요)': [
            803,
        ],
        # 라이더 재킷, 블레이저, 무스탕, 재킷, 플리스, 바람막이
        '자켓/코트(분류필요)': [
            804,
        ],
        # 데님, 면, 슬랙스, 트레이닝/조거팬츠, 스커트, 레깅스, 숏팬츠, 기타
        '팬츠(분류필요)': [
            805,
        ],
    },
    '럭셔리': {
        # 긴팔티, 반팔티
        'PK셔츠(분류필요)': [
            1428,
        ],
    },
    '스포츠': {
        # 바람막이, 아우터[기타]
        '아우터(분류필요)': [
            619,
        ],
        # 긴팔티, 반팔티, 민소매, 후드티, 기타
        '상의(분류필요)': [
            655,
        ],
        # 숏패딩/패딩조끼, 롱패딩, 바람막이, 기타
        '의류(분류필요)': [
            676,
        ],
        # 러닝화/워킹화, 스포츠화
        '신발(분류필요)': [
            658, 1479,
        ],
    },
}

# 상품 목록들(productId 기준)
hiver_products = {}
# 상품 리뷰들(productId 기준)
hiver_reviews = {}
# 중복을 제거하기 위한 상품 코드들
hiver_used = set()


# 상품 상세정보를 받아오는 함수
def test_code():
    # 대분류, 중분류
    for category_number in category_numbers['상의']['후드티']:
        category_url = f'https://capi.hiver.co.kr/v1/web/categories/{category_number}/products'
        # 한 번호당 5000개까지 조회가능
        for offset in range(0, 5001, 100):
            # 0, 100, ~, 4999
            if offset == 5000:
                offset = offset - 1

            # API 요청을 위한 헤더와 파라미터
            headers = {
                'Authorization': API_KEY,
                'User-Agent': UserAgent().random,
            }
            params = {
                'offset': offset,
                'limit': 100,
                'order': 'popular',
                'type': 'all',
                'service-type': 'hiver',
            }
            
            # 카테고리 API 요청
            category_response = requests.get(category_url, headers=headers, params=params)
            category_data = category_response.json()

            # 데이터가 존재하는 경우에만
            if category_data['data']:
                # 데이터가 존재할 때 각 상품 번호 활용
                for product in category_data['data']:
                    print('start', category_number, offset, product['id'])
                    # 사용되지 않은 프로덕트라면 세부정보 저장
                    if product['id'] not in hiver_used:
                        # 중복 기록
                        hiver_used.add(product['id'])
                        
                        # 상품 세부 정보 불러오기
                        product_url = f'https://www.hiver.co.kr/_next/data/NkOT5yhyYbV_Xxvt8xp9e/ko/products/{product['id']}.json'
                        # 상품 API 요청
                        product_response = requests.get(product_url, headers=headers)
                        product_data = product_response.json()

                        # 이미지 파싱 및 리스트에 담기
                        image_code = product_data['pageProps']['fallback'][f'@\"/products/{{id}}\",\"{product['id']}\",']['data']['text']
                        soup = BeautifulSoup(image_code, 'html.parser')
                        image_urls = []
                        image_tags = soup.find_all('img')
                        for tag in image_tags:
                            src = tag.get('src')
                            if src:
                                image_urls.append(src)

                        # 상품 정보 입력
                        hiver_products[product['id']] = {
                            'shopping_mall_id': 'hiver',
                            'main_category': 1,
                            'sub_category': 4,
                            'gender': product_data['pageProps']['fallback'][f'@\\"/products/{{id}}\\",\\"{product["id"]}\\",']['data']['option_type'],
                            'name': product_data['pageProps']['title'],
                            'price': product_data['pageProps']['fallback'][f'@\\"/products/{{id}}\\",\\"{product["id"]}\\",']['data']['sale_price'],
                            'color': [],
                            'size': [],
                            'quantity': 0, 
                            'brand_name': product_data['pageProps']['fallback'][f'@\\"/products/{{id}}\\",\\"{product["id"]}\\",']['data']['seller']['name'],
                            'fee': 0,
                            'image': product_data['pageProps']['image'],
                            'code': 0,
                            'url': f'https://www.hiver.co.kr/products/{product['id']}',
                            'detail_images': image_urls,
                            'detail_html': image_code,
                        }


    # json 파일에 저장
    with open('hiver_productIds.json', 'w', encoding='utf-8') as f:
        json.dump(hiver_productIds, f, ensure_ascii=False, indent=4)