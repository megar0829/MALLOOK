import Image, {StaticImageData} from "next/image";
import styles from "./nextModal.module.css";
import { WorldCupData} from "@/types";

export default function NextModal(props: {
	left: WorldCupData, right: WorldCupData, goNext: (data: WorldCupData) => void
}) {
	return (
		<div className={styles.background}>
			<div className={styles.container}>
				<div
					className={styles.left__container}
					onClick={() => props.goNext(props.left)}
				>
					<Image
						className={styles.image}
						src={props.left.imageUrl}
						alt={`${props.left.id} 이미지`}
						width={200}
						height={200}
						unoptimized={true}
					/>
					<div className={styles.spanDiv}>
						{
							props.left.keywordList.length &&
							props.left.keywordList.slice(0, 3).map((keyword, index) => {
								return (
									<div className={styles.keywordDiv} key={index}>
										<span className={styles.span}># {keyword}</span>
									</div>
								);
							})
						}
					</div>
				</div>

				<div className={styles.middle__container}>
					<div className={styles.line} />
					<span className={styles.vs}>VS</span>
					<div className={styles.line} />
				</div>

				<div
					className={styles.right__container}
					onClick={() => props.goNext(props.right)}
				>
					<Image
						className={styles.image}
						src={props.right.imageUrl}
						alt={`${props.right.id} 이미지`}
						width={200}
						height={200}
						unoptimized={true}
					/>
					<div className={styles.spanDiv}>
						{
							props.right.keywordList.length &&
							props.right.keywordList.slice(0, 3).map((keyword, index) => {
								return (
									<div className={styles.keywordDiv} key={index}>
										<span className={styles.span}># {keyword}</span>
									</div>
								);
							})
						}
					</div>
				</div>
			</div>
		</div>
	);
}