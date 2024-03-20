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

import {MainCategory} from "@/constants";

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
        MainCategory.map(({name, url, detailCategory}, index) => {
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
