import Link from "next/link";
import styles from "./recommend.module.css";


import MainCody from "@/app/recommend/_components/MainCody";
import MainProductList from "@/app/recommend/_components/MainProductList";

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
              <MainCody codyId={index} cody={cody} />
            </div>

            <div className={styles.rightDiv}>
              <MainProductList codyId={index} productLeft={cody.productLeft} productRight={cody.productRight}/>
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