import Link from "next/link";

import styles from "./recommend.detail.module.css";
import Image from "next/image";
import DetailCody from "@/app/recommend/_components/DetailCody";
import ProductListComponent from "@/app/_components/ProductList";
import DetailProductList from "@/app/recommend/_components/DetailProductList";



interface IParams {
  params: {scriptId: string};
}

export default async function RecommendDetailPage({params: {scriptId}} : IParams) {

  return (
    <div className={styles.background}>
      <div className={styles.container}>
        <DetailCody scriptId={scriptId} />

        <DetailProductList scriptId={scriptId} />
      </div>
    </div>
  );
}