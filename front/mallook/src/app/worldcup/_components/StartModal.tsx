import styles from "@/app/worldcup/start/worldCupStartPage.module.css";
import Image from "next/image";
import iconCrown from "@/assets/img/icons/crown.png";

export default function StartModal(props: {
	goStart: () => void;
}) {
	return (
		<div className={styles.modal__background}>
			<div className={styles.modal__container}>
				<Image className={styles.modal__image} src={iconCrown} alt="왕관 이미지"/>

				<span className={styles.modal__span}>
          패션 월드컵 8강을 시작합니다
        </span>

				<button
					className={styles.modal__button}
					onClick={() => props.goStart()}
				>시작하기
				</button>
			</div>
		</div>
	);
}