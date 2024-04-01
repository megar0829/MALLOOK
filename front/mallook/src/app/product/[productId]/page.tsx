import Link from "next/link";
import styles from "./productDetail.module.css";
import DetailProduct from "@/app/product/_components/DetailProduct";
import DetailReview from "@/app/product/_components/DetailReview";
import {ProductList} from "@/constants";
import DetailProductInformation from "@/app/product/_components/DetailProductInformation";
import ProductListComponent from "@/app/_components/ProductList";
export const metadata = {
  title: "상품 상세",
};

interface IParams {
  params: {productId: string};
}

export default async function ProductDetailPage({params: {productId}} : IParams) {
  console.log(productId);
  const productData = ProductList[Number(productId)];

  return (
    <div className={styles.container}>
      <DetailProduct productData={productData}/>

      <div className={styles.line}></div>

      <DetailProductInformation/>

      <div className={styles.line}></div>

      <div className={styles.bottomDiv}>
        <span>이 상품과 유사한 상품들</span>
      </div>
      <ProductListComponent productList={[]} />
    </div>
  );
}