import styles from "@/app/(home)/home.module.css";
import Image from "next/image";
import AfterImg from "@/assets/img/icons/land_after.png";
import imgQr from "@/assets/img/icons/qr.png";


export default function ComponentThree() {
    return (
        <div className={styles.oneTwoThreeContainer}>
            <div className={styles.threeDiv}>
                <div>
                    <Image
                        src={AfterImg}
                        alt="AfterImg"
                        style={{
                            width: "500px"
                        }}
                    />
                </div>
                <div className={styles.rightDiv}>
                    <div className={styles.rightDivTopDiv}>
                        <span className={styles.rightDivTopText}>MALLOOK</span>
                        <span className={styles.rightDivMiddleText}>App으로 보기</span>
                    </div>

                    <div className={styles.rightDivBottomDiv}>
                        <div className={styles.QRDiv}>
                            <Image src={imgQr} alt="QR"/>
                            <span className={styles.rightDivBottomText}>Android</span>
                        </div>
                        <div className={styles.QRDiv}>
                            <Image src={imgQr} alt="QR"/>
                            <span className={styles.rightDivBottomText}>ios</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}