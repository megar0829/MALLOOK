# hiver 상품 상세정보 크롤링(API)
import requests
from fake_useragent import UserAgent
import json
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
import time
from multiprocessing import Pool
from pymongo import MongoClient

client = MongoClient('mongodb+srv://root:R3MQHeXWnLco43kS@cluster0.stojj99.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0')
db = client.hiver

# Chrome 옵션 설정
options = Options()
options.add_experimental_option("detach", True)

user_agent = UserAgent().random
options.add_argument(f'user-agent={user_agent}')
options.add_argument("disable-gpu") 
options.add_argument("disable-infobars")
options.add_argument("--disable-extensions")
options.add_argument('--disable-images')
# options.add_argument('--headless')

# 속도 향상을 위한 옵션 해제
prefs = {'profile.default_content_setting_values': {
    # 'cookies' : 2, 
    'images': 2, 
    'plugins' : 2, 
    'popups': 2, 
    'geolocation': 2, 
    'notifications' : 2, 
    'auto_select_certificate': 2, 
    'fullscreen' : 2, 
    'mouselock' : 2, 
    'mixed_script': 2, 
    'media_stream' : 2, 
    'media_stream_mic' : 2, 
    'media_stream_camera': 2, 
    'protocol_handlers' : 2, 
    'ppapi_broker' : 2, 
    'automatic_downloads': 2, 
    'midi_sysex' : 2, 
    'push_messaging' : 2, 
    'ssl_cert_decisions': 2, 
    'metro_switch_to_desktop' : 2, 
    'protected_media_identifier': 2, 
    'app_banner': 2, 
    'site_engagement' : 2, 
    'durable_storage' : 2
    }
}   

options.add_experimental_option('prefs', prefs)

# 웹 드라이버 로드
driver = webdriver.Chrome(options=options)

# 암시적 대기 설정 (10초)
driver.implicitly_wait(5)

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
# 중복을 제거하기 위한 상품 코드들
hiver_used = set()

# 대분류, 소분류, 카테고리 번호 정보를 리스트로 묶음
category_info_list = []
for main_categories in category_numbers:
    for sub_categories in category_numbers[main_categories]:
        for category_number in category_numbers[main_categories][sub_categories]:
            category_info_list.append((main_categories, sub_categories, category_number))

# 상품 상세정보를 받아오는 함수
def hiver_process(category_info):
    # 분류
    main_categories, sub_categories, category_number = category_info
    print(category_number)
    category_url = f'https://capi.hiver.co.kr/v1/web/categories/{category_number}/products'
    
    # 리뷰 파라미터
    photo_params = {
        'is-first': 'false',
        'tab-type': 'photo',
        'limit': 100,
        'offset': 0,
        'version': 2101,
        'service-type': 'hiver',
    }

    text_params = {
        'is-first': 'false',
        'tab-type': 'text',
        'limit': 100,
        'offset': 0,
        'version': 2101,
        'service-type': 'hiver',
    }

    print('### offset 조회 ###')
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

        headers2 = {
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
        
        print('### 데이터 조회 ###')
        # 데이터가 존재하는 경우에만
        if category_data['data']:
            # 데이터가 존재할 때 각 상품 번호 활용
            for product in category_data['data']:
                print('start', category_number, offset, product['id'])
                # 사용되지 않은 프로덕트라면 세부정보 저장
                if product['id'] not in hiver_used:
                    print('### 세부정보 조회 ###')
                    start_time = time.time()

                    # 중복 기록
                    hiver_used.add(product['id'])
                    
                    # 상품 세부 정보 불러오기
                    product_url = f'https://www.hiver.co.kr/_next/data/NkOT5yhyYbV_Xxvt8xp9e/ko/products/{product["id"]}.json'
                    # 상품 API 요청
                    product_response = requests.get(product_url, headers=headers)
                    product_data = product_response.json()

                    # 이미지 파싱 및 리스트에 담기
                    complexity = f'@\"/products/{{id}}\",\"{product["id"]}\",'
                    image_code = product_data['pageProps']['fallback'][complexity]['data']['text']
                    soup = BeautifulSoup(image_code, 'html.parser')
                    image_urls = []
                    image_tags = soup.find_all('img')
                    for tag in image_tags:
                        src = tag.get('src')
                        if src:
                            image_urls.append(src)

                    reviews = {
                        'count': 0,
                        'reviews': []
                    }

                    print('### 리뷰정보 조회 ###')

                    # 리뷰 요청
                    review_url = f'https://hiver-api.brandi.biz/v2/web/products/{product["id"]}/reviews'
                    # 포토 리뷰 API 요청
                    photo_review_response = requests.get(review_url, headers=headers2, params=photo_params)
                    photo_review_data = photo_review_response.json()

                    # 리뷰 데이터가 있는 경우에 넣기
                    if photo_review_data['data']:
                        reviews['count'] += photo_review_data['meta']['count']
                        for photo_review in photo_review_data['data']:
                            review = {
                                'content': photo_review['text'],
                                'created_at': photo_review['created_time'],
                                'images': photo_review['user']['image_url'],
                                'point': None,
                                'product_option': [photo_review['product']['option_name'].split('/')],
                                'user_size': [photo_review['user']['height'], photo_review['user']['weight']],
                            }

                            reviews['reviews'].append(review)

                    # 텍스트 리뷰 API 요청
                    text_review_response = requests.get(review_url, headers=headers2, params=text_params)
                    text_review_data = text_review_response.json()

                    # 리뷰 데이터가 있는 경우에 넣기
                    if text_review_data['data']:
                        reviews['count'] += text_review_data['meta']['count']
                        for text_review in text_review_data['data']:
                            review = {
                                'content': text_review['text'],
                                'created_at': text_review['created_time'],
                                'images': None,
                                'point': None,
                                'product_option': [text_review['product']['option_name'].split('/')],
                                'user_size': [text_review['user']['height'], text_review['user']['weight']],
                            }

                            reviews['reviews'].append(review)
                    
                    # 태그 조회
                    tag_list = []
                    tags = product_data['pageProps']['fallback'][complexity]['data']['tags']
                    for tag in tags:
                        tag_list.append(tag['name'])

                    print('### 색상/사이즈 조회 ###')

                    # 웹 페이지 열기
                    web_url = f'https://www.hiver.co.kr/products/{product["id"]}'
                    driver.get(web_url)

                    # 버튼 클릭하여 요소 로드 대기
                    button = driver.find_element(By.CSS_SELECTOR, 'button.order.css-xnq7lu')
                    button.send_keys(Keys.ENTER)

                    # 색상 선택
                    colors = driver.execute_script('''
                        var names = [];
                        var elements = document.querySelectorAll('p.name');
                        elements.forEach(function(elem) {
                            names.push(elem.textContent.trim());
                        });
                        return names;
                    ''')

                    # 품절이 아닌 상품 클릭
                    prod_list = driver.find_elements(By.CSS_SELECTOR, 'div.bottom-modal.modal-wrap.purchaseModal.css-2aucks.modal-open li')
                    for prod in prod_list:
                        if '품절' in prod.text:
                            continue
                        else:
                            try:
                                WebDriverWait(driver, 2).until(EC.element_to_be_clickable((By.CSS_SELECTOR, 'div.bottom-modal.modal-wrap.purchaseModal.css-2aucks.modal-open li'))).click()
                                break
                            except:
                                continue

                    # 사이즈 선택
                    sizes = driver.execute_script('''
                        var sizeNames = [];
                        var sizeElements = document.querySelectorAll('details.product-option.css-zzmtgj:nth-child(2) p.name');
                        sizeElements.forEach(function(elem) {
                            sizeNames.push(elem.textContent.trim());
                        });
                        return sizeNames;
                    ''')

                    print('### 상품 정보 입력 ###')

                    # 상품 정보 입력
                    hiver_products[product['id']] = {
                        'product_id': product['id'],
                        'shopping_mall_id': 'hiver',
                        'main_category': main_categories,
                        'sub_category': sub_categories,
                        'gender': product_data['pageProps']['fallback'][complexity]['data']['option_type'],
                        'name': product_data['pageProps']['title'],
                        'price': product_data['pageProps']['fallback'][complexity]['data']['sale_price'],
                        'color': colors,
                        'size': sizes,
                        'quantity': 0, 
                        'brand_name': product_data['pageProps']['fallback'][complexity]['data']['seller']['name'],
                        'fee': 0,
                        'image': product_data['pageProps']['image'],
                        'code': None,
                        'url': f'https://www.hiver.co.kr/products/{product["id"]}',
                        'tags': tag_list,
                        'detail_images': image_urls,
                        'detail_html': image_code,
                        'reviews': reviews,
                    }

                    print('======================================================')
                    print(time.time() - start_time, '초 경과')
                    print('======================================================')
                    print()

                    print(hiver_products[product['id']]['detail_images'])

                    db.products.insert_one(hiver_products[product['id']])

if __name__ == '__main__':
    # 병렬 처리를 위한 프로세스 풀 생성
    pool = Pool(processes=20)

    # 대분류, 소분류, 카테고리 번호 정보를 리스트로 묶음
    category_info_list = []
    for main_categories in category_numbers:
        for sub_categories in category_numbers[main_categories]:
            for category_number in category_numbers[main_categories][sub_categories]:
                category_info_list.append((main_categories, sub_categories, category_number))

    # 병렬 처리를 통해 각 카테고리 정보에 대해 process_category 함수를 실행
    pool.map(hiver_process, category_info_list)

    # 프로세스 풀 종료
    pool.close()
    pool.join()

    # json 파일에 저장
    with open('hiver_details.json', 'w', encoding='utf-8') as f:
        json.dump(hiver_products, f, ensure_ascii=False, indent=4)  

print(hiver_products)