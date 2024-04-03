"use client";

import Link from "next/link";
import styles from "./productDetail.module.css";
import DetailProduct from "@/app/product/_components/DetailProduct";
import DetailReview from "@/app/product/_components/DetailReview";
import {API_URL, ProductList} from "@/constants";
import DetailProductInformation from "@/app/product/_components/DetailProductInformation";
import {useEffect, useState} from "react";
import {ProductDetail, ProductReview, Review} from "@/types";
import LoginState from "@/states/login";
import axios from "axios";


interface IParams {
  params: {productId: string};
}

export default function ProductDetailPage({params: {productId}} : IParams) {
  const {userToken} = LoginState();

  const [product, setProduct] = useState<ProductDetail>({
    brandName: "",
    code: "",
    color: [],
    detailHtml: "",
    detailImages: [],
    fee: "",
    gender: "",
    id: "",
    image: "",
    keywords: [],
    mainCategory: "",
    name: "",
    price: 0,
    review: {
      count: 0,
      averagePoint: 0,
      reviewList: []
    },
    size: [],
    subCategory: "",
    tags: "",
    url: "",
  });

  useEffect(() => {
    if (!product.id) {
      axios.get(
        `${API_URL}/api/products/${productId}`,
        {
          headers: {
            Authorization: `Bearer ${userToken.accessToken}`
          }
        }
      ).then((res) => {
        setProduct(res.data.result)
      }).catch((err) => console.log(err))
    }
  }, []);


  return (
    <div className={styles.container}>
      <DetailProduct productData={product}/>

      <div className={styles.line}></div>

      <DetailProductInformation productData={product} />

      <div className={styles.line}></div>
    </div>
  );
}