import Link from "next/link";
import styles from "./recommend.module.css";

import iconDefaultProfile from "../../../public/images/defaultUser.png";

import imgCody10 from "@/assets/img/recommend/cody10.jpg";
import imgCody10_1 from "@/assets/img/recommend/cody10-1.jpg";
import imgCody10_2 from "@/assets/img/recommend/cody10-2.jpg";
import imgCody10_3 from "@/assets/img/recommend/cody10-3.jpg";
import imgCody10_4 from "@/assets/img/recommend/cody10-4.jpg";
import imgCody10_5 from "@/assets/img/recommend/cody10-5.jpg";
import imgCody10_6 from "@/assets/img/recommend/cody10-6.jpg";

import imgCody11 from "@/assets/img/recommend/cody11.jpg";
import imgCody11_1 from "@/assets/img/recommend/cody11-1.jpg";
import imgCody11_2 from "@/assets/img/recommend/cody11-2.jpg";
import imgCody11_3 from "@/assets/img/recommend/cody11-3.jpg";
import imgCody11_4 from "@/assets/img/recommend/cody11-4.jpg";
import imgCody11_5 from "@/assets/img/recommend/cody11-5.jpg";
import imgCody11_6 from "@/assets/img/recommend/cody11-6.jpg";

import imgCody12 from "@/assets/img/recommend/cody12.jpg";
import imgCody12_1 from "@/assets/img/recommend/cody12-1.jpg";
import imgCody12_2 from "@/assets/img/recommend/cody12-2.jpg";
import imgCody12_3 from "@/assets/img/recommend/cody12-3.jpg";
import imgCody12_4 from "@/assets/img/recommend/cody12-4.jpg";
import imgCody12_5 from "@/assets/img/recommend/cody12-5.jpg";
import imgCody12_6 from "@/assets/img/recommend/cody12-6.jpg";

import imgCody13 from "@/assets/img/recommend/cody13.jpg";
import imgCody14 from "@/assets/img/recommend/cody14.jpg";
import imgCody15 from "@/assets/img/recommend/cody15.jpg";
import imgCody16 from "@/assets/img/recommend/cody16.jpg";
import imgCody17 from "@/assets/img/recommend/cody17.jpg";
import imgCody18 from "@/assets/img/recommend/cody18.jpg";
import imgCody19 from "@/assets/img/recommend/cody19.jpg";
import imgCody20 from "@/assets/img/recommend/cody20.jpg";
import imgCody21 from "@/assets/img/recommend/cody21.jpg";
import imgCody22 from "@/assets/img/recommend/cody22.jpg";
import imgCody23 from "@/assets/img/recommend/cody23.jpg";
import imgCody24 from "@/assets/img/recommend/cody24.jpg";
import imgCody25 from "@/assets/img/recommend/cody25.jpg";
import imgCody26 from "@/assets/img/recommend/cody26.jpg";
import imgCody27 from "@/assets/img/recommend/cody27.jpg";
import imgCody28 from "@/assets/img/recommend/cody28.jpg";
import imgCody29 from "@/assets/img/recommend/cody29.jpg";
import imgCody30 from "@/assets/img/recommend/cody30.jpg";

import Cody from "@/app/recommend/_components/Cody";
import ProductList from "@/app/recommend/_components/ProductList";

export const metadata = {
  title: "추천",
};

import {CodyList, CodyData} from "@/constants";

export default function RecommendPage() {

  const codyList = () => {
    return (
      CodyList.map((codyList: CodyData, index: number) => {
        return (
          <div className={styles.cody__container} key={index}>
            <div className={styles.leftDiv}>
              <Cody codyList={codyList} />
            </div>

            <div className={styles.rightDiv}>
              <ProductList productLeft={codyList.productsLeft} productRight={codyList.productRight}/>
            </div>
          </div>
        );
      })
    );
  }

  return (
    <div className={styles.container}>
      {codyList()}
    </div>
  );
}