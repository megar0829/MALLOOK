"use client";

import Link from "next/link";
import styles from "./masonry.module.css";
import { MasonryInfiniteGrid } from "@egjs/react-infinitegrid";


import {CodyBookList} from "@/constants";
import Image from "next/image";
import {useState} from "react";

export interface GroupData {
	nextGroupKey: number;
	count: number;
}

function getItems({ nextGroupKey, count }:GroupData) {
	const nextItems = [];
	const nextKey = nextGroupKey * count;

	for (let i = 0; i < count; ++i) {
		nextItems.push({
			groupKey: nextGroupKey,
			key: nextKey + i
		});
	}
	return nextItems;
}

const Item = ({ num }: any) => {
	return (
		<div className={styles.item}>
			<div className={styles.thumbnail}>
				<Image
					src={`https://naver.github.io/egjs-infinitegrid/assets/image/${(num % 33) + 1}.jpg`}
					alt="egjs"
					style={{
						width: "100%",
						height: "auto",
						borderRadius: 8
					}}
					width={500}
					height={500}
				/>
				<div className={styles.cover}></div>
			</div>
			<div>

			</div>
			<div className={styles.info}>{`egjs ${num}`}</div>
		</div>
	);
}

export default function Masonry() {
	const [items, setItems] = useState(() => getItems({nextGroupKey: 0, count: 10}));

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
					...getItems({nextGroupKey: nextGroupKey, count: 10}),
				]);
			}}
			onRenderComplete={(e) => {
				console.log(e);
			}}
		>
			{items.map((item, index) => <Item data-grid-groupkey={item.groupKey} key={index} num={item.key} />)}
		</MasonryInfiniteGrid>
	);
}