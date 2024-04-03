import Image, {StaticImageData} from "next/image";
import styles from "./nextModal.module.css";

interface NextProps {
	url: string | StaticImageData;
	index: number | null;
}

export default function NextModal(props: {
	left: NextProps, right: NextProps, goNext: ({url, index}: NextProps) => void
}) {
	return (
		<div className={styles.background}>
			<div className={styles.container}>
				<div
					className={styles.left__container}
					onClick={() => props.goNext({url: props.left.url, index: props.left.index})}
				>
					<Image className={styles.image} src={props.left.url} alt={`${props.left.index} 번 이미지`} />
					{/*<div className={styles.spanDiv}>*/}
					{/*	<span className={styles.span}>{!props.left.index ? 0 : props.left.index  + 1}번 이미지</span>*/}
					{/*</div>*/}
				</div>

				<div className={styles.middle__container}>
					<div className={styles.line} />
					<span className={styles.vs}>VS</span>
					<div className={styles.line} />
				</div>

				<div
					className={styles.right__container}
					onClick={() => props.goNext({url: props.right.url, index: props.right.index})}
				>
					<Image className={styles.image} src={props.right.url} alt={`${props.right.index} 번 이미지`}/>
					{/*<div className={styles.spanDiv}>*/}
					{/*	<span className={styles.span}>{!props.right.index? 0 : props.right.index + 1}번 이미지</span>*/}
					{/*</div>*/}
				</div>
			</div>
		</div>
	);
}