"use client";

import React, { useState } from 'react'
import styles from "../../styles/product.module.css";

import Image from 'next/image';

import iconDefault from "../../../public/images/default.png";

const mainCategory = [
  "상의", 
  "하의", 
  "신발", 
  "모자", 
  "가방"
];

const detailCategory = {
  "상의": [
    "긴팔티", 
    "카라티", 
    "반팔티", 
    "민소매", 
    "후드티", 
    "맨투맨", 
    "니트 / 스웨터", 
    "셔츠 / 블라우스",  
    "기타",  
  ],
  "하의": [
    "데님", 
    "면", 
    "슬랙스", 
    "트레이닝 / 조거 팬츠", 
    "스커트", 
    "레깅스", 
    "숏 팬츠", 
    "기타",  
  ],
  "아우터": [
    "숏패딩 / 패딩조끼", 
    "롱패딩", 
    "숏코트", 
    "롱코트", 
    "라이더 재킷", 
    "블레이저", 
    "무스탕", 
    "재킷", 
    "점퍼", 
    "플리스", 
    "가디건", 
    "후드집업", 
    "바람막이", 
    "기타", 
  ],
  "원피스": [
    "롱원피스", 
    "미니원피스", 
    "기타", 
  ],
  "가방": [
    "크로스백", 
    "숄더백", 
    "토트백", 
    "클러치", 
    "에코 / 캔버스 백", 
    "백팩", 
    "웨이스트백", 
    "기타", 
  ],
  "신발": [
    "스니커즈", 
    "러닝화 / 워킹화", 
    "스포츠화", 
    "구두", 
    "힐 / 펌프스", 
    "로퍼", 
    "뮬 / 블로퍼", 
    "플랫 슈즈", 
    "부츠", 
    "샌들 / 슬리퍼", 
    "기타",
   ],
  "모자": [ 
    "볼캡 / 야구모자", 
    "스냅백", 
    "비니", 
    "버킷햇", 
    "베레모", 
    "페도라", 
    "기타"
  ]
}

export default function Category() {
  const [isDetail, setIsDetail] = useState(false);

  const categoryList = () => {
    if (!isDetail) {
      return (
        mainCategory.map((catogory, index) => {
          return (
            <div>
              <div>
                <Image src={iconDefault} alt='카테고리 기본이미지' />
              </div>
              <span>{catogory}</span>
            </div>
          );
        })
      );
    } else {
      return (
        <div></div>
      );
    }
  }

  return (
    <div className={styles.category__container}>
      {categoryList()}
    </div>
  )
}
