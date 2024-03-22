"use client";

import styles from "./profile.module.css";
import {ProfileSample} from "@/constants";
import Image from "next/image";
import { Line, Circle } from 'rc-progress';
import {useState} from "react";
import Link from "next/link";


export default function Profile() {
	const [isGrade, setIsGrade] = useState(false);

	return (
		<div className={styles.container}>
			<div className={styles.leftDiv}>
				<Image className={styles.profileImg} src={ProfileSample.profileImg} alt="프로필 이미지" />
			</div>

			<div className={styles.middleDiv}>
				<div className={styles.middleDiv__topDiv}>
					<span className={styles.middleDiv__topDiv__nickname}>{ProfileSample.nickname}</span>
				</div>

				<div className={styles.middleDiv__middleDiv}>
					<span className={styles.middleDiv__middleDiv__level}>LV. {Math.floor((ProfileSample.exp / 100))}</span>
					<div className={styles.middleDiv__middleDiv__expBarDiv}>
						<Line className={styles.middleDiv__middleDiv__expBar} percent={ProfileSample.exp} strokeWidth={1} strokeColor="#FFA8A8" />
						<span className={styles.middleDiv__middleDiv__exp}>{ProfileSample.exp} %</span>
					</div>
					<div
						className={styles.middleDiv__middleDiv__benefitDiv}
						onClick={() => setIsGrade(!isGrade)}
					>
						<span className={styles.middleDiv__middleDiv__benefit}>등급혜택 {!isGrade ? "▽" : "△"}</span>
					</div>
				</div>

				<div className={styles.middleDiv__bottomDiv}>
					<span className={styles.middleDiv__bottomDiv__point}>포인트 {ProfileSample.point} p</span>

					<div className={styles.middleDiv__bottomDiv__couponDiv}>
						<span className={styles.middleDiv__bottomDiv__coupon}>쿠폰</span>
					</div>
					<div className={styles.middleDiv__bottomDiv__orderListDiv}>
						<span className={styles.middleDiv__bottomDiv__orderLisk}>주문목록</span>
					</div>
				</div>
			</div>
			
			<div className={styles.rightDiv}>
				<Link href="/worldcup">
					<div className={styles.worldCupDiv}>
						<span className={styles.worldCup}>월드컵</span>
					</div>
				</Link>
			</div>
		</div>
	);
}