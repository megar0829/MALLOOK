"use client";

import Link from "next/link";
import styles from "./product.module.css";

import Category from "@/app/product/_components/Category";
import MainRecommend from "@/app/product/_components/MainRecommend";
import ProductListComponent from "@/app/_components/ProductList";
import MainProductList from "@/app/product/_components/MainProductList";
import {Dispatch, SetStateAction, useState} from "react";


export default function ProductPage() {
  const [chooseCategory, setChooseCategory] = useState<string>("top100");
  const [chooseDetailCategory, setChooseDetailCategory] = useState<string>("");

  return (
    <div className={styles.container}>
      <Category setChooseCategory={setChooseCategory} setChooseDetailCategory={setChooseDetailCategory} />
      <MainRecommend />
      <MainProductList chooseCategory={chooseCategory} chooseDetailCategory={chooseDetailCategory} />
    </div>
  );
}
