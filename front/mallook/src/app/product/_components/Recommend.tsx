"use client";

import React from 'react'
import styles from "./recommend.module.css";
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

import Image, {StaticImageData} from "next/image";

import useEmblaCarousel from "embla-carousel-react";

export type ProductList = {
  productImg: string | StaticImageData,
  name: string,
  brand: string,
  price: number,
}

const productList: ProductList[] = [
  {
    productImg: imgProduct1,
    name: "상품 1",
    brand: "mallook",
    price: 120000,
  },
  {
    productImg: imgProduct2,
    name: "상품 2",
    brand: "mallook",
    price: 120000,
  },
  {
    productImg: imgProduct3,
    name: "상품 3",
    brand: "mallook",
    price: 120000,
  },
  {
    productImg: imgProduct4,
    name: "상품 4",
    brand: "mallook",
    price: 120000,
  },{
    productImg: imgProduct5,
    name: "상품 5",
    brand: "mallook",
    price: 120000,
  },
  {
    productImg: imgProduct6,
    name: "상품 6",
    brand: "mallook",
    price: 120000,
  },
  {
    productImg: imgProduct7,
    name: "상품 7",
    brand: "mallook",
    price: 120000,
  },{
    productImg: imgProduct8,
    name: "상품 8",
    brand: "mallook",
    price: 120000,
  },
  {
    productImg: imgProduct9,
    name: "상품 9",
    brand: "mallook",
    price: 120000,
  },{
    productImg: imgProduct10,
    name: "상품 10",
    brand: "mallook",
    price: 120000,
  },
]


export default function Recommend() {

  const [emblaRef, emblaApi] = useEmblaCarousel({ loop: false });

  const recommendList = () => {
    return(
      productList.map((product, index) => {
        return (
          <div key={index} className={styles.recommend__productListDiv}>
            <div className={styles.recommend__innerDiv}>
              <div className={styles.recommend__product__imageDiv}>
                <Image className={styles.recommend__product__image} src={product.productImg} alt="상품이미지"/>
              </div>

              <div className={styles.recommend__textDiv}>
                <span className={styles.recommend__product__name}>{product.name}</span>
                <span className={styles.recommend__product__brand}>{product.brand}</span>
                <span className={styles.recommend__product__price}>{product.price.toLocaleString()} 원</span>
              </div>
            </div>
          </div>
        );
      })
    );
  }

  return (
    <div className={styles.recommend__container}>
      <span className={styles.recommend__title}>추천 상품</span>

      <div className={styles.recommend__carousel} ref={emblaRef}>
        <div className={styles.recommend__carousel__container}>
          {recommendList()}
        </div>
      </div>
    </div>
  )
}
