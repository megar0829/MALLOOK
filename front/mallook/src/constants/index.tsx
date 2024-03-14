import iconDefaultProfile from "../../public/images/defaultUser.png";

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
import {StaticImageData} from "next/image";

export interface Product {
    productImg: StaticImageData | String;
    name: string;
    brand: string;
    price: number;
}

export interface CodyData {
    codyImg: StaticImageData | String;
    codyName: string;
    profileImg: StaticImageData | String;
    username: string;
    productLeft: Product[];
    productRight: Product[];
}

export interface ProductListProps {
    productLeft: Product[];
    productRight: Product[];
}

export interface CodyBookData {
    profileImg: StaticImageData | String;
    username: string;
    content: string;
    likeCnt: number;
    codyImg: StaticImageData | String;
}

export const CodyList: CodyData = [
    {
        codyImg: imgCody10,
        codyName: "심플한 코디",
        profileImg: iconDefaultProfile,
        username: "무신소리예요?",
        productsLeft: [
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
        productsLeft: [
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
        productsLeft: [
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
