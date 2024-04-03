"use client";

import styles from "./scripts.module.css";
import {useState} from "react";

import {CodyList} from "@/constants";
import Image from "next/image";

export default function Scripts() {
	const [isMine, setIsMine] = useState(true);

	const chooseLikeScripts = () => {
		setIsMine(false);
	};
	const chooseMyScripts = () => {
		setIsMine(true);
	}

	const scripts = () => {
		if (isMine) {
			return (
				CodyList.map((cody, index) => {
					return (
						<div className={styles.scripts__bottomDiv__container} key={index}>
							<div className={styles.scripts__bottomDiv__cover}></div>
							<Image className={styles.scripts__bottomDiv__image} src={cody.codyImg} alt="코디 이미지" />
							<div className={styles.scripts__bottomDiv__nameDiv}>
								<span className={styles.scripts__bottomDiv__name}>{cody.codyName}</span>
							</div>
						</div>
					);
				})
			);
		} else {
			return (
				<div className={styles.scripts__noneDiv}>
					<span className={styles.scripts__none}>아직 좋아요를 누른 코디가 없습니다.</span>
				</div>
			);
		}
	}

	return (
		<div className={styles.scripts__container}>
			<div className={styles.scripts__topDiv}>
				<div className={styles.scripts__topDiv__textDiv} onClick={() => chooseMyScripts()}>
					<span
						className={styles.scripts__topDiv__textSpan}
						style={{
							color: isMine ? "black" : "gray"
						}}
					>
						나의 스크립트
					</span>
				</div>
				<div className={styles.scripts__topDiv__textDiv} onClick={() => chooseLikeScripts()}>
					<span
						className={styles.scripts__topDiv__textSpan}
						style={{
							color: !isMine ? "black" : "gray"
						}}
					>
						좋아요 누른 코디
					</span>
				</div>
			</div>
			
			<div className={styles.scripts__bottomDiv}>
				{scripts()}
			</div>
		</div>
	);
}