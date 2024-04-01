import styles from "@/app/(home)/home.module.css";
import Image from "next/image";
import imgPhone from "@/assets/img/icons/phone.png";

export default function RightDiv() {
	return (
		<div className={styles.rightDiv}>
			<div className={styles.phoneDiv}>
				<Image src={imgPhone} alt="Phone"/>
			</div>
		</div>
	);
}