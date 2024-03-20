import Link from "next/link";
import styles from "./recommend.module.css";


import Cody from "@/app/recommend/_components/Cody";
import ProductList from "@/app/recommend/_components/ProductList";

export const metadata = {
  title: "추천",
};

import {CodyList} from "@/constants";

export default function RecommendPage() {

  const codyList = () => {
    return (
      CodyList.map((cody, index) => {
        return (
          <div className={styles.cody__container} key={index}>
            <div className={styles.leftDiv}>
              <Cody cody={cody} />
            </div>

            <div className={styles.rightDiv}>
              <ProductList productLeft={cody.productLeft} productRight={cody.productRight}/>
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