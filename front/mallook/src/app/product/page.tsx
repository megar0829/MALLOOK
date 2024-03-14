import Link from "next/link";
import styles from "./product.module.css";

import Category from "@/app/product/_components/category";
import Recommend from "@/app/product/_components/recommend";
import Product from "@/app/product/_components/product";

export const metadata = {
  title: "상품",
};

export default async function ProductPage() {

  return (
    <div className={styles.container}>
      {/*<Category />*/}
      <Recommend />
      <Product />
    </div>
  );
}
