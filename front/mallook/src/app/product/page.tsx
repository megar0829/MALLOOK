import Link from "next/link";
import styles from "./product.module.css";

import Category from "@/app/product/_components/Category";
import Recommend from "@/app/product/_components/Recommend";
import Product from "@/app/product/_components/Product";

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
