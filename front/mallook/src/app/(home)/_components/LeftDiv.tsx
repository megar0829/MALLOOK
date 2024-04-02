import styles from "@/app/(home)/home.module.css";
import Image from "next/image";
import imgQr from "@/assets/img/icons/qr.png";

export default function LeftDiv() {
	return (
		<div className={styles.leftDiv}>
			<div className={styles.leftDivTopDiv}>
				<span className={styles.leftDivTopText}>MALLOOK</span>
				<span className={styles.leftDivMiddleText}>App으로 보기</span>
			</div>

			<div className={styles.leftDivBottomDiv}>
				<div className={styles.QRDiv}>
					<Image src={imgQr} alt="QR"/>
					<span className={styles.leftDivBottomText}>Android</span>
				</div>
				<div className={styles.QRDiv}>
					<Image src={imgQr} alt="QR"/>
					<span className={styles.leftDivBottomText}>ios</span>
				</div>
			</div>
		</div>
	);
}