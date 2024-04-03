"use client";

import styles from "./profile.module.css";
import {ProfileSample} from "@/constants";
import Image from "next/image";
import { Line, Circle } from 'rc-progress';
import {useState} from "react";
import Link from "next/link";
import GetProfileImage from "@/utils/GetProfileImage";
import ShopImage from "@/assets/img/icons/shop.jpg"
import CouponImage from "@/assets/img/icons/coupon.jpg"
import WordCupImage from "@/assets/img/icons/wordcup.jpg"

export default function Profile() {
	const [isGrade, setIsGrade] = useState(false);
	const expPercentage = Math.round((ProfileSample.exp / ProfileSample.expRange[1]) * 1000) / 10;

	return (
		<div className={styles.container}>
			<div className={styles.leftDiv}>
				<div className={styles.profileImgDiv}>
					<Image src={GetProfileImage(ProfileSample.level)} alt="프로필 이미지"/>
				</div>
				<div className={styles.leftDiv__nickname}>
					<span>{ProfileSample.nickname}</span>
				</div>
				<div className={styles.leftDiv__info}>
					<div className={styles.leftDiv__info__top}>
						<span className={styles.leftDiv__info__level}>LV. {ProfileSample.level.slice(-1)}</span>
						<span className={styles.leftDiv__info__point}>{ProfileSample.point} point</span>
					</div>
					<div className={styles.leftDiv__info__bottom}>
						<Line className={styles.leftDiv__info__expBar} percent={expPercentage} strokeWidth={1}
							  strokeColor="#7C88C8"/>
						<span className={styles.leftDiv__info__exp}>{expPercentage}%</span>
					</div>
				</div>
			</div>
			<div className={styles.rightDiv}>
				<div
					className={styles.rightDiv__item}
					style={{
						backgroundImage: `url(${ShopImage.src})`,
						backgroundPosition: 'center',
						backgroundSize: 'cover'
					}}
				>
					<div className={styles.rightDiv__item__cover}>Order List</div>
				</div>
				<div
					className={styles.rightDiv__item}
					style={{
						backgroundImage: `url(${CouponImage.src})`,
						backgroundPosition: 'center',
						backgroundSize: 'cover'
					}}
				>
					<div className={styles.rightDiv__item__cover}>Coupon</div>
				</div>
				<Link
					className={styles.rightDiv__item}
					href="/worldcup"
					style={{
						backgroundImage: `url(${WordCupImage.src})`,
						backgroundPosition: 'center',
						backgroundSize: 'cover'
					}}
				>
					<div className={styles.rightDiv__item__cover}>Word Cup</div>
				</Link>
			</div>
		</div>
	);
}