# 크롤링
## 카테고리
- **상의**
    - 긴팔티
        - 카라티
    - 반팔티
        - 반팔카라티
    - 민소매
        - 난닝구 포함
    - 후드티
        - 반팔 후드도 포함
    - 맨투맨
    - 니트 / 스웨터
        - 조끼도 포함
    - 셔츠 / 블라우스
        - 반팔 / 긴팔
    - 기타
        - 스포츠 딸려오면 기타로 넣기
- **하의**
    - 데님
    - 면
    - 슬랙스
    - 트레이닝 / 조거 팬츠
    - 스커트
    - 레깅스
    - 숏 팬츠
    - 기타
        - 점프 슈트 / 오버올
- **아우터**
    - 숏패딩 / 패딩조끼
    - 롱패딩
    - 숏코트
    - 롱코트
    - 라이더 재킷
    - 블레이저
    - 무스탕
    - 재킷
        - 청자켓 포함
    - 점퍼
    - 플리스
    - 가디건
    - 후드집업
    - 바람막이
    - 기타
- **원피스**
    - 롱원피스
    - 미니원피스
    - 기타
- **가방**
    - 크로스백
    - 숄더백
    - 토트백
        - 더플백 포함
    - 클러치
    - 에코 / 캔버스 백
    - 백팩
    - 웨이스트백
    - 기타
- **신발**
    - 스니커즈
    - 러닝화 / 워킹화
    - 스포츠화
        - 농구, 축구
    - 구두
    - 힐 / 펌프스
    - 로퍼
    - 뮬 / 블로퍼
    - 플랫 슈즈
    - 부츠
    - 샌들 / 슬리퍼
        - 크록스도 포함
    - 기타
- **모자**
    - 볼캡 / 야구모자
    - 스냅백
    - 비니
    - 버킷햇
    - 베레모
    - 페도라
    - 기타

## 쇼핑몰 목록
### [29cm](https://www.29cm.co.kr/home/)
#### 카테고리
- Request 
    - authority: cache.29cm.co.kr
    - method: GET
    - path: /item/category
    - params
        - category1_code
            - 여성의류: 268100100
            - 여성가방: 269100100
            - 여성신발: 270100100
            - 남성의류: 272100100
            - 남성가방: 273100100
            - 남성신발: 274100100
        - sortBy: VISIT_COUNT
- Response
```
{
    "count": 1,
    "next": null,
    "previous": null,
    "results": [
        {
            "category": {
                "category_name": "남성의류",
                "category_code": 272100100,
                "category2": [
                    {
                        "category_name": "상의",
                        "category_code": 272103100,
                        "category3": [
                            {
                                "category_name": "스웨트셔츠",
                                "category_code": 272103107,
                                "count": 0
                            },
                        ],
                        "count": 0
                    },
                    ...
                ],
                "count": 0
            }
        }
    ]
}
```

#### 상품 리스트
- Request 
     - authority: search-api.29cm.co.kr
     - method: GET
     - path: /api/v4/products/category
     - payload(params)
        - categoryLargeCode: 268100100
        - categoryMediumCode: 268103100
        - categorySmallCode: 268103102
        - count: 50
        - page: 1
        - minPrice: 
        - maxPrice: 
        - isFreeShipping: 
        - isDiscount: 
        - sort: latest
        - init: T
        - colors: 
        - excludeSoldOut: false
        - brands: 

- Response
```
{
    "result": "SUCCESS",
    "data": {
        "products": [
            {
                "itemNo": 2362647,
                "itemName": "Bon Bon Elbow Patch (LGY) ASJU241KR74-LGY",
                "frontBrandNo": 5724,
                "frontBrandNameKor": "세인트제임스",
                "frontBrandNameEng": "SAINT JAMES",
                "categoryNames": null,
                "consumerPrice": 178000,
                "sellPrice": 178000,
                "imageUrl": "/item/202402/11eed5fb319093568377bfb110ecb0ea.jpg",
                "visibleBeginTimestamp": "2023-12-05T18:18:59.486320+09:00",
                "visibleEndTimestamp": null,
                "useWithoutVisibleEndDate": "T",
                "availableBeginTimestamp": "2023-12-05T17:10:07.831640+09:00",
                "availableEndTimestamp": null,
                "useWithoutAvailableEndDate": null,
                "discountRate": 0,
                "couponDiscountRate": 10,
                "isCouponAllowedInOrder": true,
                "heartCount": 2826,
                "reviewCount": 33,
                "reviewAveragePoint": 5.0,
                "isSoldOut": false,
                "lastSalePrice": 160200,
                "lastSalePercent": 10,
                "subjectDescriptions": [],
                "isFreeShipping": false,
                "isNew": false,
                "heartOn": false,
                "saleInfoV2": {
                    "consumerPrice": 178000,
                    "sellPrice": 178000,
                    "saleRate": 0,
                    "couponSaleRate": 10,
                    "isCoupon": true,
                    "totalSellPrice": 160200,
                    "totalSaleRate": 10
                },
                "eventTags": []
            },
            ...
        ],
        "productsTotalCount": 11930,
        "metaAttributes": {
            "isUserRecommendationHit": null,
            "userRecommendHit": -1
        }
    },
    "message": null,
    "errorCode": null
}
```

#### 상품 상세
- html

#### 리뷰
- Request 
    - authority: review-api.29cm.co.kr
    - method: GET
    - path: /api/v4/reviews
    - params
        - itemId: 2362647
        - page: 0
        - size: 20
        - sort: 
- Response
```
{
    "result": "SUCCESS",
    "data": {
        "count": 33,
        "giftCount": 0,
        "averagePoint": 5.0,
        "results": [
            {
                "itemReviewNo": 8201337,
                "optionValue": [
                    "[사이즈]S"
                ],
                "userNo": 4137430,
                "userId": "bam-***",
                "point": 5,
                "contents": "노란기 없는 그레이라 좋아요\n그런데 뭔가 기존 봉봉들보다 면이 탄탄하지 않은느낌? 약건 흐물? 거려욤! 165 55사이즈인 분들은 스몰사세용 ",
                "reviewType": 0,
                "registrationType": "USER",
                "isGift": "F",
                "isReported": "F",
                "giftReview": null,
                "userSize": [
                    "165cm",
                    "54kg"
                ],
                "uploadFiles": [
                    {
                        "filename": "IMG_2891.jpeg",
                        "contentType": "image/jpeg",
                        "url": "/next-product/2024/03/08/1e13caac4d1e43038a17ea33822a7286_20240308124813.jpeg",
                        "size": 1815336,
                        "isDeleted": "F",
                        "insertTimestamp": "2024-03-08 12:48:53",
                        "updatedTimestamp": "2024-03-08 12:48:53"
                    }
                ],
                "partnerComment": null,
                "surveyList": [
                    {
                        "surveyType": "SIZE",
                        "optionValue": 2
                    }
                ],
                "insertTimestamp": "2024-03-08 12:48:53",
                "orderNo": null,
                "orderSerial": null,
                "adminId": null,
                "adminName": null
            },
            ...
        ],
        "next": null,
        "previous": null,
        "purchaserCount": 0,
        "totalCount": 0
    },
    "message": null,
    "errorCode": null
}
```

### [MUSINSA](https://www.musinsa.com/app/)
### [ABLY](https://m.a-bly.com/)