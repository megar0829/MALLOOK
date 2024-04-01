import Link from "next/link";
import styles from "./product.module.css";

import Category from "@/app/product/_components/Category";
import MainRecommend from "@/app/product/_components/MainRecommend";
import ProductListComponent from "@/app/_components/ProductList";
import MainProductList from "@/app/product/_components/MainProductList";

export const metadata = {
  title: "상품",
};

export default async function ProductPage() {

  return (
    <div className={styles.container}>
      <Category />
      <MainRecommend />
      <MainProductList />
    </div>
  );
}
