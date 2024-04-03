"use client";

import Link from "next/link";
import styles from "./masonry.module.css";
import { MasonryInfiniteGrid } from "@egjs/react-infinitegrid";

import iconDefault from "@/assets/img/icons/defaultUser.png";
import {CodyBookData} from "@/types";
import {CodyBookList} from "@/constants";
import Image, {StaticImageData} from "next/image";
import {useState} from "react";

import { GoHeart } from "react-icons/go";
import { GoHeartFill } from "react-icons/go";
import { AiOutlineUser } from "react-icons/ai";

export interface GroupData {
	nextGroupKey: number;
}

export interface ItemData {
	groupKey: number;
	cody: CodyBookData;
}

export interface Item {
	groupKey: number;
	key: number;
	cody: CodyBookData;
}

function getItems({ nextGroupKey }:GroupData) {
	const nextItems:ItemData[] = [];

	CodyBookList.map((cody) => {
		const item = {
			groupKey: nextGroupKey,
			cody: cody
		}
		nextItems.push(item);
	})

	return nextItems;
}

const Item = (props: { postData:ItemData }) => {
	return (
		<div className={styles.item}>
			<div className={styles.innerDiv}>
				<div className={styles.topDiv}>
					<Image
						src={props.postData.cody.codyImg}
						alt="egjs"
						style={{
							width: "100%",
							height: "auto",
						}}
						width={500}
						height={500}
					/>
				</div>
				<div className={styles.bottomDiv}>
					<div className={styles.bottomDiv__topDiv}>
						<div className={styles.bottomDiv__topDiv__leftDiv}>
							<Image className={styles.bottomDiv__topDiv__leftDiv__profileImage} src={props.postData.cody.profileImg} alt="프로필이미지" />
							<span className={styles.bottomDiv__topDiv__leftDiv__username}>{props.postData.cody.username}</span>
						</div>
						<div className={styles.bottomDiv__topDiv__rightDiv}>
							<span className={styles.bottomDiv__topDiv__rightDiv__likeCnt}>{props.postData.cody.likeCnt}</span>
							<div className={styles.bottomDiv__topDiv__rightDiv__likeDiv}>
								<GoHeart className={styles.bottomDiv__topDiv__rightDiv__like} style={{color: "#292929"}}/>
								{/*active heart*/}
								{/*<GoHeartFill className={styles.bottomDiv__topDiv__rightDiv__like} style={{color: "#E7707C"}}/>*/}
							</div>
						</div>
					</div>

					<div className={styles.bottomDiv__bottomDiv}>
						<span className={styles.bottomDiv__bottomDiv__content}>{props.postData.cody.content}</span>
					</div>
				</div>
			</div>
		</div>
	);
}

export default function Masonry() {
	const [items, setItems] = useState(() => getItems({nextGroupKey: 0}));

	// const items = getItems({nextGroupKey: 0, count: 10});

	return (
		<MasonryInfiniteGrid
			className={styles.container}
			gap={1}
			align={"justify"}
			onRequestAppend={(e) => {
				const nextGroupKey = (+e.groupKey! || 0);

				setItems([
					...items,
					...getItems({nextGroupKey}),
				]);
			}}
			onRenderComplete={(e) => {
				console.log(e);
			}}
		>
			{items.map((item, index) => {
				return(
					<Item
						data-grid-groupkey={item.groupKey}
						key={index}
						postData={item}
					/>
				);
			})}
		</MasonryInfiniteGrid>
	);
}