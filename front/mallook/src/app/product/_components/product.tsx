import React from 'react'
import styles from "../product.module.css";

import Image from "next/image";

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

const productList = [
  {
    productImg: imgProduct1,
    name: "상품 1",
    brand: "mallook",
    price: "120,000",
  },
  {
    productImg: imgProduct2,
    name: "상품 2",
    brand: "mallook",
    price: "120,000",
  },
  {
    productImg: imgProduct3,
    name: "상품 3",
    brand: "mallook",
    price: "120,000",
  },
  {
    productImg: imgProduct4,
    name: "상품 4",
    brand: "mallook",
    price: "120,000",
  },{
    productImg: imgProduct5,
    name: "상품 5",
    brand: "mallook",
    price: "120,000",
  },
  {
    productImg: imgProduct6,
    name: "상품 6",
    brand: "mallook",
    price: "120,000",
  },
  {
    productImg: imgProduct7,
    name: "상품 7",
    brand: "mallook",
    price: "120,000",
  },{
    productImg: imgProduct8,
    name: "상품 8",
    brand: "mallook",
    price: "120,000",
  },
  {
    productImg: imgProduct9,
    name: "상품 9",
    brand: "mallook",
    price: "120,000",
  },{
    productImg: imgProduct10,
    name: "상품 10",
    brand: "mallook",
    price: "120,000",
  },
]

export default function Product() {

  const recommendList = () => {
    productList.map((product, index) => {
      return (
        <div key={index}>
          <div>
            <Image src={product.productImg} alt="상품이미지"/>
          </div>
          <span>{product.name}</span>
          <span>{product.brand}</span>
          <span>{product.price} 원</span>
        </div>
      );
    })
  }

  return (
    <div>
      <span>추천 상품</span>
      <div>

      </div>
    </div>
  )
}