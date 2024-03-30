import iconDefaultProfile from "@/assets/img/icons/defaultUser.png";

import imgCody10 from "@/assets/img/recommend/cody10.jpg";
import imgCody10_1 from "@/assets/img/recommend/cody10-1.jpg";
import imgCody10_2 from "@/assets/img/recommend/cody10-2.jpg";
import imgCody10_3 from "@/assets/img/recommend/cody10-3.jpg";
import imgCody10_4 from "@/assets/img/recommend/cody10-4.jpg";
import imgCody10_5 from "@/assets/img/recommend/cody10-5.jpg";
import imgCody10_6 from "@/assets/img/recommend/cody10-6.jpg";

import imgCody11 from "@/assets/img/recommend/cody11.jpg";
import imgCody11_1 from "@/assets/img/recommend/cody11-1.jpg";
import imgCody11_2 from "@/assets/img/recommend/cody11-2.jpg";
import imgCody11_3 from "@/assets/img/recommend/cody11-3.jpg";
import imgCody11_4 from "@/assets/img/recommend/cody11-4.jpg";
import imgCody11_5 from "@/assets/img/recommend/cody11-5.jpg";
import imgCody11_6 from "@/assets/img/recommend/cody11-6.jpg";

import imgCody12 from "@/assets/img/recommend/cody12.jpg";
import imgCody12_1 from "@/assets/img/recommend/cody12-1.jpg";
import imgCody12_2 from "@/assets/img/recommend/cody12-2.jpg";
import imgCody12_3 from "@/assets/img/recommend/cody12-3.jpg";
import imgCody12_4 from "@/assets/img/recommend/cody12-4.jpg";
import imgCody12_5 from "@/assets/img/recommend/cody12-5.jpg";
import imgCody12_6 from "@/assets/img/recommend/cody12-6.jpg";

import imgCody13 from "@/assets/img/recommend/cody13.jpg";
import imgCody14 from "@/assets/img/recommend/cody14.jpg";
import imgCody15 from "@/assets/img/recommend/cody15.jpg";
import imgCody16 from "@/assets/img/recommend/cody16.jpg";
import imgCody17 from "@/assets/img/recommend/cody17.jpg";
import imgCody18 from "@/assets/img/recommend/cody18.jpg";
import imgCody19 from "@/assets/img/recommend/cody19.jpg";
import imgCody20 from "@/assets/img/recommend/cody20.jpg";
import imgCody21 from "@/assets/img/recommend/cody21.jpg";
import imgCody22 from "@/assets/img/recommend/cody22.jpg";
import imgCody23 from "@/assets/img/recommend/cody23.jpg";
import imgCody24 from "@/assets/img/recommend/cody24.jpg";
import imgCody25 from "@/assets/img/recommend/cody25.jpg";
import imgCody26 from "@/assets/img/recommend/cody26.jpg";
import imgCody27 from "@/assets/img/recommend/cody27.jpg";
import imgCody28 from "@/assets/img/recommend/cody28.jpg";
import imgCody29 from "@/assets/img/recommend/cody29.jpg";
import imgCody30 from "@/assets/img/recommend/cody30.jpg";

import imgProduct1 from "@/assets/img/product/product1.jpg";
import imgProduct2 from "@/assets/img/product/product2.jpg";
import imgProduct3 from "@/assets/img/product/product3.jpg";
import imgProduct4 from "@/assets/img/product/product4.jpg";
import imgProduct5 from "@/assets/img/product/product5.jpg";
import imgProduct6 from "@/assets/img/product/product6.jpg";
import imgProduct7 from "@/assets/img/product/product7.jpg";
import imgProduct8 from "@/assets/img/product/product8.jpg";
import imgProduct9 from "@/assets/img/product/product9.jpg";
import imgProduct10 from "@/assets/img/product/product10.jpg";

import {StaticImageData} from "next/image";

import iconTop from "@/assets/img/category/top.jpg";
import iconBottom from "@/assets/img/category/bottom.jpg";
import iconOuter from "@/assets/img/category/outer.jpg";
import iconOnepiece from "@/assets/img/category/onepiece.jpg";
import iconBag from "@/assets/img/category/bag.jpg";
import iconShoe from "@/assets/img/category/shoe.jpg";
import iconHat from "@/assets/img/category/hat.jpg";

export interface Product {
    productImg: string | StaticImageData;
    name: string;
    brand: string;
    price: number;
}

export interface CodyData {
    codyImg: string | StaticImageData;
    codyName: string;
    profileImg: string | StaticImageData;
    username: string;
    productLeft: Product[];
    productRight: Product[];
}

export interface ProductListProps {
    productLeft: Product[];
    productRight: Product[];
}

export interface CodyBookData {
    profileImg: string | StaticImageData;
    username: string;
    content: string;
    likeCnt: number;
    codyImg: string | StaticImageData;
}

export interface DetailCategoryData {
    categoryName: string,
    categoryUrl: string | StaticImageData
}

export interface MainCategoryData {
    name: string,
    url: string | StaticImageData ,
    detailCategory: DetailCategoryData[]
}

export interface ProfileData {
    nickname: string;
    birth: string;
    gender: string;
    phone: string;
    point: number;
    exp: number;
    city: string;
    district: string;
    address: string;
    zipcode: string;
}

export interface ProfileSampleData {
    nickname: string;
    point: number;
    exp: number;
    profileImg: string | StaticImageData;
}

export interface ReviewData {
    content: string;
    created_at: string | Date;
    images: string | StaticImageData;
    point: string | null;
    product_option: any [];
    user_size: number [];
}

export const ReviewList: ReviewData[] = [
    {
        "content": "품질이 생각보다 좋고,\n만족스러워서 가성비가 좋아요!!",
        "created_at": "1709521906",
        "images": "https://image.brandi.me/user/2023/04/12/2745488802_1681270328_L.jpg",
        "point": null,
        "product_option": [
            [
                "플리츠 긴팔 셔츠_베이지",
                "2XL",
                "단품구매"
            ]
        ],
        "user_size": [
            174,
            0
        ]
    },
    {
        "content": "사이즈가.. 좀 크네요 ㅠㅠ 그리규 많이 얇아요.. 한여름에 입어야될듯..\n잘입을게여 ",
        "created_at": "1696679693",
        "images": "https://image.brandi.me/user/2019/05/18/249541332666728_1558127295_L.jpg",
        "point": null,
        "product_option": [
            [
                "플리츠 긴팔 셔츠_화이트",
                "L",
                "단품구매"
            ]
        ],
        "user_size": [
            170,
            65
        ]
    },
    {
        "content": "찰랑 찰랑 거리는 원단에 하얀색이라 비치긴 한데 세로 줄로  포인트가 있어 일반 셔츠 보다 예뻐요",
        "created_at": "1693609075",
        "images": "https://image.brandi.me/user/2023/01/01/2604668272_1672573740_L.jpg",
        "point": null,
        "product_option": [
            [
                "플리츠 긴팔 셔츠_화이트",
                "2XL",
                "단품구매"
            ]
        ],
        "user_size": [
            174,
            67
        ]
    },
    {
        "content": "핏 겁나 이뻐요 소재도 뭔가 그 뽀송뽀송한 느낌이라해야하나 암튼 비침도 없고 착용감 너무 부드러워서 좋네요 ㅜㅜ맨날 이거만 입어요 사가성비 ㄹㅈㄷ",
        "created_at": "1688757651",
        "images": "https://image.brandi.me/user/2021/01/05/105376015725155624779_1609852307_L.jpg",
        "point": null,
        "product_option": [
            [
                "플리츠 긴팔 셔츠_화이트",
                "XL",
                "단품구매"
            ]
        ],
        "user_size": [
            172,
            58
        ]
    }
]

export const ProductList: Product[] = [
    {
        productImg: imgCody10_1,
        name: "빅 트위치 로고 티셔츠 화이트",
        brand: "리",
        price: 39000
    },
    {
        productImg: imgCody10_2,
        name: "Deep One Tuck Sweat Pants [Grey]",
        brand: "제로",
        price: 35100
    },
    {
        productImg: imgCody10_3,
        name: "LENTO(렌토) 블랙 Glasses",
        brand: "세미콜론 아이웨어",
        price: 39900
    },
    {
        productImg: imgCody10_4,
        name: "에라 - 인센스:트루 화이트 / VN0A54F14G51",
        brand: "반스",
        price: 49900
    },
    {
        productImg: imgCody10_5,
        name: "빅 트위치 로고 티셔츠 네이비",
        brand: "리",
        price: 39000
    },
    {
        productImg: imgCody10_6,
        name: "EL 라디우스 와이드 스웨트 팬츠 그레이",
        brand: "엘리메노",
        price: 38000
    },
    {
        productImg: imgCody11_1,
        name: "크루넥 리브 니트 스웨터_아이보리",
        brand: "블론드나인",
        price: 29800
    },
    {
        productImg: imgCody11_2,
        name: "Classic Sweat Pants [Black]",
        brand: "제로",
        price: 39000
    },
    {
        productImg: imgCody11_3,
        name: "Black_Double Ring Necklace",
        brand: "월간",
        price: 12000
    },
    {
        productImg: imgCody11_4,
        name: "에어스니커즈 찰리 블랙",
        brand: "키치오브제",
        price: 128000
    },
    {
        productImg: imgCody11_5,
        name: "램스울 크루넥 오버 니트 (아이보리)",
        brand: "테이크이지",
        price: 37800
    },
    {
        productImg: imgCody11_6,
        name: "글로리 모먼트 스웨트 팬츠_블랙",
        brand: "마카사이트",
        price: 29900
    },
    {
        productImg: imgCody12_1,
        name: "CAMPING AURORA KNIT BLACK",
        brand: "크리틱",
        price: 59200
    },
    {
        productImg: imgCody12_2,
        name: "Classic Sweat Pants [Grey]",
        brand: "제로",
        price: 39000
    },
    {
        productImg: imgCody12_3,
        name: "SVB-#175 뱅글 체인팔찌",
        brand: "쇼브오프",
        price: 21600
    },
    {
        productImg: imgCody12_4,
        name: "선셋 비치 오버핏 니트 스웨터_블랙",
        brand: "스테이지 네임",
        price: 56950
    },
    {
        productImg: imgCody12_5,
        name: "글로리 모먼트 스웨트 팬츠_멜란지 그레이",
        brand: "마카사이트",
        price: 29900
    },
    {
        productImg: imgCody12_6,
        name: "SVB-#178 체인팔찌",
        brand: "쇼브오프",
        price: 15200
    }
]

export const CodyList: CodyData[] = [
    {
        codyImg: imgCody10,
        codyName: "심플한 코디",
        profileImg: iconDefaultProfile,
        username: "무신소리예요?",
        productLeft: [
            {
                productImg: imgCody10_1,
                name: "빅 트위치 로고 티셔츠 화이트",
                brand: "리",
                price: 39000
            },
            {
                productImg: imgCody10_2,
                name: "Deep One Tuck Sweat Pants [Grey]",
                brand: "제로",
                price: 35100
            },
            {
                productImg: imgCody10_3,
                name: "LENTO(렌토) 블랙 Glasses",
                brand: "세미콜론 아이웨어",
                price: 39900
            }
        ],
        productRight: [
            {
                productImg: imgCody10_4,
                name: "에라 - 인센스:트루 화이트 / VN0A54F14G51",
                brand: "반스",
                price: 49900
            },
            {
                productImg: imgCody10_5,
                name: "빅 트위치 로고 티셔츠 네이비",
                brand: "리",
                price: 39000
            },
            {
                productImg: imgCody10_6,
                name: "EL 라디우스 와이드 스웨트 팬츠 그레이",
                brand: "엘리메노",
                price: 38000
            }
        ]
    },
    {
        codyImg: imgCody11,
        codyName: "힙하다 힙해",
        profileImg: iconDefaultProfile,
        username: "귀여운패피윤정이",
        productLeft: [
            {
                productImg: imgCody11_1,
                name: "크루넥 리브 니트 스웨터_아이보리",
                brand: "블론드나인",
                price: 29800
            },
            {
                productImg: imgCody11_2,
                name: "Classic Sweat Pants [Black]",
                brand: "제로",
                price: 39000
            },
            {
                productImg: imgCody11_3,
                name: "Black_Double Ring Necklace",
                brand: "월간",
                price: 12000
            }
        ],
        productRight: [
            {
                productImg: imgCody11_4,
                name: "에어스니커즈 찰리 블랙",
                brand: "키치오브제",
                price: 128000
            },
            {
                productImg: imgCody11_5,
                name: "램스울 크루넥 오버 니트 (아이보리)",
                brand: "테이크이지",
                price: 37800
            },
            {
                productImg: imgCody11_6,
                name: "글로리 모먼트 스웨트 팬츠_블랙",
                brand: "마카사이트",
                price: 29900
            }
        ]
    },
    {
        codyImg: imgCody12,
        codyName: "조거 팬츠가 대세",
        profileImg: iconDefaultProfile,
        username: "귀여운패피세진이",
        productLeft: [
            {
                productImg: imgCody12_1,
                name: "CAMPING AURORA KNIT BLACK",
                brand: "크리틱",
                price: 59200
            },
            {
                productImg: imgCody12_2,
                name: "Classic Sweat Pants [Grey]",
                brand: "제로",
                price: 39000
            },
            {
                productImg: imgCody12_3,
                name: "SVB-#175 뱅글 체인팔찌",
                brand: "쇼브오프",
                price: 21600
            }
        ],
        productRight: [
            {
                productImg: imgCody12_4,
                name: "선셋 비치 오버핏 니트 스웨터_블랙",
                brand: "스테이지 네임",
                price: 56950
            },
            {
                productImg: imgCody12_5,
                name: "글로리 모먼트 스웨트 팬츠_멜란지 그레이",
                brand: "마카사이트",
                price: 29900
            },
            {
                productImg: imgCody12_6,
                name: "SVB-#178 체인팔찌",
                brand: "쇼브오프",
                price: 15200
            }
        ]
    }
];

export const CodyBookList: CodyBookData[] = [
    {
        profileImg: iconDefaultProfile,
        username: "hee_seop",
        content: "❤️",
        likeCnt: 44,
        codyImg: imgCody10
    },
    {
        profileImg: iconDefaultProfile,
        username: "hyeockgun_",
        content: "가디건 입자 이제",
        likeCnt: 14,
        codyImg: imgCody11
    },
    {
        profileImg: iconDefaultProfile,
        username: "doyoon25",
        content: "몰룩 소통해요💕💕💕",
        likeCnt: 126,
        codyImg: imgCody12
    },
    {
        profileImg: iconDefaultProfile,
        username: "hypefashionzip",
        content: "개강룩 필요해? 봄 코디 추천 모음",
        likeCnt: 20,
        codyImg: imgCody13
    },
    {
        profileImg: iconDefaultProfile,
        username: "vo_pie",
        content: "웨크웨어 코디",
        likeCnt: 14,
        codyImg: imgCody14
    },
    {
        profileImg: iconDefaultProfile,
        username: "ju______",
        content: "#아카이브챌린지 #EASY챌린지 #스트릿코디 #봄신발 #MALLOOK #맨투맨추천 #바람막이코디 #신발추천",
        likeCnt: 14,
        codyImg: imgCody15
    },
    {
        profileImg: iconDefaultProfile,
        username: "wap_s",
        content: "쌀쌀",
        likeCnt: 24,
        codyImg: imgCody16
    },
    {
        profileImg: iconDefaultProfile,
        username: "hyeockgun_",
        content: "가디건 입자 이제",
        likeCnt: 14,
        codyImg: imgCody17
    },
    {
        profileImg: iconDefaultProfile,
        username: "doyoon25",
        content: "몰룩 소통해요💕💕💕",
        likeCnt: 126,
        codyImg: imgCody18
    },
    {
        profileImg: iconDefaultProfile,
        username: "vo_pie",
        content: "웨크웨어 코디",
        likeCnt: 14,
        codyImg: imgCody19
    },
    {
        profileImg: iconDefaultProfile,
        username: "wap_s",
        content: "쌀쌀",
        likeCnt: 24,
        codyImg: imgCody20
    },
]

export const MainCategory : MainCategoryData[] = [
    {
        name: "상의",
        url: iconTop,
        detailCategory: [
            {
                categoryName: "긴팔티",
                categoryUrl: iconTop
            },
            {
                categoryName: "카라티",
                categoryUrl: iconTop
            },
            {
                categoryName: "반팔티",
                categoryUrl: iconTop
            },
            {
                categoryName: "민소매",
                categoryUrl: iconTop
            },
            {
                categoryName: "후드티",
                categoryUrl: iconTop
            },
            {
                categoryName: "맨투맨",
                categoryUrl: iconTop
            },
            {
                categoryName: "니트 / 스웨터",
                categoryUrl: iconTop
            },
            {
                categoryName: "셔츠 / 블라우스",
                categoryUrl: iconTop
            },
            {
                categoryName: "기타",
                categoryUrl: iconTop
            }
        ]
    },
    {
        name: "하의",
        url: iconBottom,
        detailCategory: [
            {
                categoryName: "데님",
                categoryUrl: iconBottom
            },
            {
                categoryName: "면",
                categoryUrl: iconBottom
            },
            {
                categoryName: "슬랙스",
                categoryUrl: iconBottom
            },
            {
                categoryName: "트레이닝 / 조거 팬츠",
                categoryUrl: iconBottom
            },
            {
                categoryName: "스커트",
                categoryUrl: iconBottom
            },
            {
                categoryName: "레깅스",
                categoryUrl: iconBottom
            },
            {
                categoryName: "숏 팬츠",
                categoryUrl: iconBottom
            },
            {
                categoryName: "기타",
                categoryUrl: iconBottom
            }
        ]
    },
    {
        name: "아우터",
        url: iconOuter,
        detailCategory: [
            {
                categoryName: "숏패딩 / 패딩조끼",
                categoryUrl: iconOuter
            },
            {
                categoryName: "롱패딩",
                categoryUrl: iconOuter
            },
            {
                categoryName: "숏코트",
                categoryUrl: iconOuter
            },
            {
                categoryName: "롱코트",
                categoryUrl: iconOuter
            },
            {
                categoryName: "라이더 재킷",
                categoryUrl: iconOuter
            },
            {
                categoryName: "블레이저",
                categoryUrl: iconOuter
            },
            {
                categoryName: "무스탕",
                categoryUrl: iconOuter
            },
            {
                categoryName: "재킷",
                categoryUrl: iconOuter
            },
            {
                categoryName: "점퍼",
                categoryUrl: iconOuter
            },
            {
                categoryName: "플리스",
                categoryUrl: iconOuter
            },
            {
                categoryName: "가디건",
                categoryUrl: iconOuter
            },
            {
                categoryName: "후드집업",
                categoryUrl: iconOuter
            },
            {
                categoryName: "바람막이",
                categoryUrl: iconOuter
            },
            {
                categoryName: "기타",
                categoryUrl: iconOuter
            }
        ]
    },
    {
        name: "원피스",
        url: iconOnepiece,
        detailCategory: [
            {
                categoryName: "롱원피스",
                categoryUrl: iconOnepiece
            },
            {
                categoryName: "미니원피스",
                categoryUrl: iconOnepiece
            },
            {
                categoryName: "기타",
                categoryUrl: iconOnepiece
            }
        ]
    },
    {
        name: "가방",
        url: iconBag,
        detailCategory: [
            {
                categoryName: "크로스백",
                categoryUrl: iconBag
            },
            {
                categoryName: "숄더백",
                categoryUrl: iconBag
            },
            {
                categoryName: "토트백",
                categoryUrl: iconBag
            },
            {
                categoryName: "클러치",
                categoryUrl: iconBag
            },
            {
                categoryName: "에코 / 캔버스 백",
                categoryUrl: iconBag
            },
            {
                categoryName: "백팩",
                categoryUrl: iconBag
            },
            {
                categoryName: "웨이스트백",
                categoryUrl: iconBag
            },
            {
                categoryName: "기타",
                categoryUrl: iconBag
            }
        ]
    },
    {
        name: "신발",
        url: iconShoe,
        detailCategory: [
            {
                categoryName: "스니커즈",
                categoryUrl: iconShoe
            },
            {
                categoryName: "러닝화 / 워킹화",
                categoryUrl: iconShoe
            },
            {
                categoryName: "스포츠화",
                categoryUrl: iconShoe
            },
            {
                categoryName: "구두",
                categoryUrl: iconShoe
            },
            {
                categoryName: "힐 / 펌프스",
                categoryUrl: iconShoe
            },
            {
                categoryName: "로퍼",
                categoryUrl: iconShoe
            },
            {
                categoryName: "뮬 / 블로퍼",
                categoryUrl: iconShoe
            },
            {
                categoryName: "플랫 슈즈",
                categoryUrl: iconShoe
            },
            {
                categoryName: "플랫 슈즈",
                categoryUrl: iconShoe
            },
            {
                categoryName: "부츠",
                categoryUrl: iconShoe
            },
            {
                categoryName: "샌들 / 슬리퍼",
                categoryUrl: iconShoe
            },
            {
                categoryName: "기타",
                categoryUrl: iconShoe
            }
        ]
    },
    {
        name: "모자",
        url: iconHat,
        detailCategory: [
            {
                categoryName: "볼캡 / 야구모자",
                categoryUrl: iconHat
            },
            {
                categoryName: "스냅백",
                categoryUrl: iconHat
            },
            {
                categoryName: "비니",
                categoryUrl: iconHat
            },
            {
                categoryName: "버킷햇",
                categoryUrl: iconHat
            },
            {
                categoryName: "베레모",
                categoryUrl: iconHat
            },
            {
                categoryName: "페도라",
                categoryUrl: iconHat
            },
            {
                categoryName: "기타",
                categoryUrl: iconHat
            }
        ]
    }
];

export const ProfileSample: ProfileSampleData = {
    nickname: "무신소리예요?",
    point: 2000,
    exp: 40,
    profileImg: imgProduct9
};