"use client";

import React, {SetStateAction, useCallback, useState} from 'react'
import styles from "./category.module.css";

import Image, {StaticImageData} from 'next/image';

import useEmblaCarousel from "embla-carousel-react";

import iconDefault from "../../../../public/images/default.png";

import iconTop from "@/assets/img/category/top.jpg";
import iconBottom from "@/assets/img/category/bottom.jpg";
import iconOuter from "@/assets/img/category/outer.jpg";
import iconOnepiece from "@/assets/img/category/onepiece.jpg";
import iconHat from "@/assets/img/category/hat.jpg";
import iconShoe from "@/assets/img/category/shoe.jpg";
import iconBag from "@/assets/img/category/bag.jpg";
import iconTop100 from "@/assets/img/product/top100.jpg";
import iconBack from "@/assets/img/product/back.png";
import iconLeft from "@/assets/img/product/left.png"
import iconRight from "@/assets/img/product/right.png"
import {number} from "prop-types";


const mainCategory : MainCategory[] = [
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

const detailCategory = {
  "상의": [
    ["긴팔티", iconTop],
    ["카라티", iconTop],
    ["반팔티", iconTop],
    ["민소매", iconTop],
    ["후드티", iconTop],
    ["맨투맨", iconTop],
    ["니트 / 스웨터", iconTop],
    ["셔츠 / 블라우스", iconTop],
    ["기타", iconTop],
  ],
  "하의": [
    ["데님", iconBottom],
    ["면", iconBottom],
    ["슬랙스", iconBottom],
    ["트레이닝 / 조거 팬츠", iconBottom],
    ["스커트", iconBottom],
    ["레깅스", iconBottom],
    ["숏 팬츠", iconBottom],
    ["기타", iconBottom],
  ],
  "아우터": [
    ["숏패딩 / 패딩조끼", iconOuter],
    ["롱패딩", iconOuter],
    ["숏코트", iconOuter],
    ["롱코트", iconOuter],
    ["라이더 재킷", iconOuter],
    ["블레이저", iconOuter],
    ["무스탕", iconOuter],
    ["재킷", iconOuter],
    ["점퍼", iconOuter],
    ["플리스", iconOuter],
    ["가디건", iconOuter],
    ["후드집업", iconOuter],
    ["바람막이", iconOuter],
    ["기타", iconOuter],
  ],
  "원피스": [
    ["롱원피스", iconOnepiece],
    ["미니원피스", iconOnepiece],
    ["기타", iconOnepiece],
  ],
  "가방": [
    ["크로스백", iconBag],
    ["숄더백", iconBag],
    ["토트백", iconBag],
    ["클러치", iconBag],
    ["에코 / 캔버스 백", iconBag],
    ["백팩", iconBag],
    ["웨이스트백", iconBag],
    ["기타", iconBag],
  ],
  "신발": [
    ["스니커즈", iconShoe],
    ["러닝화 / 워킹화", iconShoe],
    ["스포츠화", iconShoe],
    ["구두", iconShoe],
    ["힐 / 펌프스", iconShoe],
    ["로퍼", iconShoe],
    ["뮬 / 블로퍼", iconShoe],
    ["플랫 슈즈", iconShoe],
    ["부츠", iconShoe],
    ["샌들 / 슬리퍼", iconShoe],
    ["기타", iconShoe],
   ],
  "모자": [ 
    ["볼캡 / 야구모자", iconHat],
    ["스냅백", iconHat],
    ["비니", iconHat],
    ["버킷햇", iconHat],
    ["베레모", iconHat],
    ["페도라", iconHat],
    ["기타", iconHat],
  ]
}

type DetailCategory = { categoryName: string, categoryUrl: string | StaticImageData };
type MainCategory = { name: string, url: string | StaticImageData , detailCategory: DetailCategory[]};

export default function Category() {
  const [isDetail, setIsDetail] = useState(false);
  const [categoryDetail, setCategoryDetail] = useState([]);

  const [emblaRef, emblaApi] = useEmblaCarousel({ loop: false })

  const openCategory = (detailCategory:any) => {
    setIsDetail(true);
    setCategoryDetail(detailCategory);
    // emblaApi?.scrollTo(0);
  }

  const closeCategory = () => {
    setIsDetail(false);
  }

  const leftCircle = () => {
    if (!isDetail) {
      return (
        <div className={styles.category__categoryDiv}>
          <div className={styles.category__imageDiv}>
            <Image className={styles.category__image} src={iconTop100} alt='몰룩 랭킹'/>
          </div>
          <span className={styles.category__spanDiv}>몰룩 랭킹</span>
        </div>
      )
    } else {
      return (
        <div className={styles.category__categoryDiv} onClick={() => closeCategory()}>
          <div className={styles.category__imageDiv}>
            <Image className={styles.category__image} src={iconBack} alt="뒤로가기"/>
          </div>
          <span className={styles.category__spanDiv}>뒤로가기</span>
        </div>
      )
    }
  }

  const categoryList = () => {
    if (!isDetail) {
      return (
        mainCategory.map(({name, url, detailCategory}, index) => {
          return (
            <div key={index} className={styles.category__categoryDiv} onClick={() => openCategory(detailCategory)}>
              <div className={styles.category__innerDiv}>
                <div className={styles.category__imageDiv}>
                  <Image className={styles.category__image} src={url} alt='카테고리 이미지' />
                </div>
                <span className={styles.category__spanDiv}>{name}</span>
              </div>
            </div>
          );
        })
      );
    } else {
      return (
        categoryDetail && categoryDetail.map(({ categoryUrl, categoryName }, index ) => {
          return (
            <div key={index} className={styles.category__categoryDiv}>
              <div className={styles.category__innerDiv}>
                <div className={styles.category__imageDiv}>
                  <Image className={styles.category__image} src={categoryUrl} alt='카테고리 이미지' />
                </div>
                <span className={styles.category__spanDiv}>{categoryName}</span>
              </div>
            </div>
          );
        })
      );
    }
  }

  return (
    <div className={styles.category__embla} ref={emblaRef}>
      <div
        className={styles.category__carousel}
        style={{
         justifyContent: categoryDetail.length > 10 ? "flex-start" : "center"
        }}
      >
        {leftCircle()}
        {categoryList()}
      </div>
    </div>
  )
}
