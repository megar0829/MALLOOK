import Image from "next/image";
import styles from "./loadingModal.module.css";
import LoadingIcon from "@/assets/img/icons/loading_icon.png";
import LoadingText from "@/assets/img/icons/loading_text.png";

export default function LoadingModal() {
	return (
		<div className={styles.background}>
			<div className={styles.loadingComponent}>
				<Image
					src={LoadingIcon}
					alt="LoadingIcon"
					className={styles.rotatingImage}
					style={{
						width: "200px"
					}}
				/>
				<Image
					src={LoadingText}
					alt="LoadingText"
					style={{
						width: "200px"
					}}
				/>
			</div>
		</div>
	);
}