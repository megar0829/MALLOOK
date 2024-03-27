"use client";

import Image from "next/image";
import {CodyList} from "@/constants";
import styles from "./detailCody.module.css";

export default function DetailCody( props:{codyId: string} ) {
	const index = Number(props.codyId);
	const cody = CodyList[index];

	return (
		<div className={styles.cody__container}>
			<div className={styles.cody__leftDiv}>
				<Image className={styles.cody__leftDiv__image} src={cody.codyImg} alt="코디 이미지"/>
			</div>
.
			<div className={styles.cody__rightDiv}>
				<div className={styles.cody__rightDiv__topDiv}>
					<Image className={styles.cody__rightDiv__topDiv__profileImage} src={cody.profileImg} alt="유저 프로필 이미지"/>
					<span className={styles.cody__rightDiv__topDiv__username}>{cody.username}</span>
				</div>

				<div className={styles.cody__rightDiv__bottomDiv}>
					<span className={styles.cody__rightDiv__bottomDiv__codyName}>{cody.codyName}</span>
					<span className={styles.cody__rightDiv__bottomDiv__span}># 하늘하늘 #간질간질</span>
				</div>
			</div>
		</div>
	);
}