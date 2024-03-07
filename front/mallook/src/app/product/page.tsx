import Link from "next/link";
import styles from "../../styles/product.module.css";

import Category from "@/containers/product/category";
import Recommend from "@/containers/product/recommend";
import Product from "@/containers/product/product";

export const metadata = {
  title: "상품",
};

export default async function ProductPage() {

  return (
    <div className={styles.container}>
      <Category />
      <Recommend />
      <Product />
    </div>
  );
}
