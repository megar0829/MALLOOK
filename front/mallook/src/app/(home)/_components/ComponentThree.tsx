import styles from "@/app/(home)/home.module.css";
import Image from "next/image";
import Link from "next/link";
import AfterImg from "@/assets/img/icons/land_after.png";
import ImgQr from "@/assets/img/icons/qr.png";
import LogoSm from "@/assets/img/icons/logo_sm.png";
import { WiDirectionRight } from "react-icons/wi";


export default function ComponentThree() {
    return (
        <div className={styles.threeContainer}>
            <div className={styles.threeLeftDiv}>
                <div className={styles.threeLeftDivText}>
                    <div className={styles.appIcon}>
                        <Image src={LogoSm} alt="LogoSm"/>
                    </div>
                    <div className={styles.appText}>
                        <h1>App으로 보기</h1>
                    </div>
                </div>
                <div className={styles.qrDiv}>
                    <div className={styles.qrDivItem}>
                        <Image src={ImgQr} alt="QR"/>
                        <p>Android</p>
                    </div>
                    <div className={styles.qrDivItem}>
                        <Image src={ImgQr} alt="QR"/>
                        <p>ios</p>
                    </div>
                </div>
            </div>
            <div className={styles.threeRightDiv}>
                <Link href="/recommend" className={styles.recommendButton}>
                    <h1>상품 추천받으러 가기</h1>
                    <WiDirectionRight 
                        style={{
                            fontSize: "60px"
                        }}
                    />
                </Link>
                <Image
                    src={AfterImg}
                    alt="AfterImg"
                    style={{
                        height: "auto",
                        width: "70%"
                    }}
                />
            </div>
        </div>
    );
}