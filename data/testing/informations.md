# product 엔티티

## 인스턴스

### 각 속성과 크롤링해야하는 부분
- shopping_mall_id: 쇼핑몰 PK
  - 쇼핑몰명
- main_category: 대분류(Enum)
  - 상품 카테고리(대분류) -> 기준 만들기
- sub_category: 소분류(Enum)
  - 상품 카테고리(중분류) -> 기준 만들기
- gender: 성별(Enum)
  - 상품 성별(공용, 남성, 여성) -> 기준 만들기
- name: 이름
  - 상품 명
- price: 가격
  - 상품 가격
- quantity: 양
  - 품절인지 아닌지 확인하기
- brand_name: 브랜드명
  - 브랜드 명
- size: 사이즈
  - 사이즈 어떤 종류가 있는지
- color: 색상
  - 색상 어떤 종류가 있는지
- fee: 배송비
  - 배송비는 일단 가져와보기
- image: 이미지 url
  - 이미지들 다 따오기(썸네일, 세부사항)
- code: 상품 코드
  - 상품의 일련번호(고유코드)
- url: 상품 정보 url
  - 이 상품에 대한 url도 저장하기