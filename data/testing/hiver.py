# hiver 크롤링(API)
import requests
from fake_useragent import UserAgent
import json

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

hiver_productIds = {
    '상의': {
        '긴팔티': [],
    }
}

# 상품 카테고리별 productId 가져오기
# def product_ids():
#     category = 0
#     url = f'https://capi.hiver.co.kr/v1/web/categories/{}/products?offset=100&limit=100&order=popular&type=all&service-type=hiver'

    
def product_ids():
    for category, subcategories in category_numbers.items():
        for subcategory, subcategory_ids in subcategories.items():
            for subcategory_id in subcategory_ids:
                url = f'https://capi.hiver.co.kr/v1/web/categories/{subcategory_id}/products'
                for offset in range(0, 5001, 100):
                    # 0, 100, ~, 4999
                    if offset == 5000:
                        offset = offset - 1
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

                    response = requests.get(url, headers=headers, params=params)
                    data = response.json()

                    if not data['data']:
                        break
                    for product in data['data']:
                        print(product['id'])
                        if category not in hiver_productIds:
                            hiver_productIds[category] = {}
                        else:
                            if subcategory not in hiver_productIds[category]:
                                hiver_productIds[category][subcategory] = []
                            else:
                                hiver_productIds[category][subcategory].append(product['id'])


def test_code():
    for number in category_numbers['상의']['긴팔티']:
        url = f'https://capi.hiver.co.kr/v1/web/categories/{number}/products'
        for offset in range(0, 5001, 100):
            # 0, 100, ~, 4999
            if offset == 5000:
                offset = offset - 1
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

            response = requests.get(url, headers=headers, params=params)
            data = response.json()

            if not data['data']:
                break
            for product in data['data']:
                print(number, product['id'])
                hiver_productIds['상의']['긴팔티'].append(product['id'])

    with open('hiver_productIds.json', 'w') as f:
        json.dump(hiver_productIds, f, ensure_ascii=False, indent=4)

def f():
    url = 'https://hiver-api.brandi.biz/v1/web/categories'
    params = {
        'service-type': 'hiver'
    }
    response = requests.get(url, params=params)

    try:
        response.raise_for_status()
        print("Request was successful!")
    except requests.exceptions.HTTPError as err:
        print(f"HTTP Error: {err}")

    results = response.json()
    return results


print(f())

print(hiver_productIds)