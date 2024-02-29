import Link from "next/link";
import styles from "../../styles/home.module.css";
import Image from "next/image";

import imgQr from "../../../public/images/qr.png";
import imgPhone from "../../../public/images/phone.png";

export const metadata = {
  title: "Home",
};

export default async function HomePage() {

  return (
    <div className={styles.container}>
      <div className={styles.leftDiv}>
        <div className={styles.leftDivTopDiv}>
          <span className={styles.leftDivTopText}>패션스쿨</span>
          <span className={styles.leftDivMiddleText}>App으로 보기</span>
        </div>

        <div className={styles.leftDivBottomDiv}>
          <div className={styles.QRDiv}>
            <Image src={imgQr} alt="QR" />
            <span className={styles.leftDivBottomText}>Android</span>
          </div>
          <div className={styles.QRDiv}>
            <Image src={imgQr} alt="QR" />
            <span className={styles.leftDivBottomText}>ios</span>
          </div>
        </div>
      </div>

      <div className={styles.rightDiv}>
        <div className={styles.phoneDiv}>
          <Image src={imgPhone} alt="Phone" />
        </div>
      </div>
    </div>
  );
}