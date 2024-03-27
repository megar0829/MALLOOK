import Link from "next/link";

import styles from "./recommend.detail.module.css";
import Image from "next/image";
import DetailCody from "@/app/recommend/_components/DetailCody";
import ProductListComponent from "@/app/_components/ProductList";

export const metadata = {
  title: `추천 상세`,
};

interface IParams {
  params: {scriptId: string};
}

export default async function RecommendDetailPage({params: {scriptId}} : IParams) {

  return (
    <div className={styles.background}>
      <div className={styles.container}>
        <DetailCody codyId={scriptId} />

        <ProductListComponent />
      </div>
    </div>
  );
}