"use client";

import React, {Dispatch, SetStateAction, useCallback, useState} from 'react'
import styles from "./category.module.css";

import Image, {StaticImageData} from 'next/image';

import useEmblaCarousel from "embla-carousel-react";


import iconTop100 from "@/assets/img/icons/logo_sm.png";
import iconBack from "@/assets/img/product/back.png";

import {MainCategory} from "@/constants";

export default function Category(props: {
  setChooseCategory: Dispatch<SetStateAction<string>>,
  setChooseDetailCategory: Dispatch<SetStateAction<string>>
}) {
  const [isDetail, setIsDetail] = useState(false);
  const [categoryDetail, setCategoryDetail] = useState([]);

  const [emblaRef, emblaApi] = useEmblaCarousel({ loop: false })

  const openCategory = (name: string, detailCategory:any) => {
    setIsDetail(true);
    setCategoryDetail(detailCategory);
    props.setChooseCategory(name);
    // emblaApi?.scrollTo(0);
  }

  const closeCategory = () => {
    setIsDetail(false);
  }

  const onClickCategory =  (name: string) => {
    props.setChooseDetailCategory(name);
  }

  const leftCircle = () => {
    if (!isDetail) {
      return (
        <div className={styles.category__categoryDiv}>
          <div className={styles.category__imageDiv} onClick={() => onClickCategory("top100")}>
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
        MainCategory.map(({name, url, detailCategory}, index) => {
          return (
            <div key={index} className={styles.category__categoryDiv} onClick={() => openCategory(name, detailCategory)}>
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
            <div key={index} onClick={() => onClickCategory(categoryName)} className={styles.category__categoryDiv}>
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
