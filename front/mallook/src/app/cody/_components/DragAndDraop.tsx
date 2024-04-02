"use client";

import { useEffect, useRef, useState } from 'react';
import Boundary from "./Boundary";
import styles from "./dragAndDrop.module.css";
import Image, {StaticImageData} from "next/image";
import {ProductList} from "@/constants";
import registDragEvent from "@/utils/registDragEvent";
import update from "immutability-helper";
import {inrange} from "@/utils";
import html2canvas from "html2canvas";
import {saveAs} from "file-saver";

const BOUNDARY_MARGIN = 12;
const MIN_W = 80;
const MIN_H = 80;

export interface ItemData {
	x: number;
	y: number;
	w: number;
	h: number;
	url: string | StaticImageData
}

export default function DragAndDraop() {
	const product = ProductList.slice(0, 9).map((item) => item.productImg);

	const [dragItems, setDragItems] = useState<ItemData[]>([]);

	const boundaryRef = useRef<HTMLDivElement>(null);
	const divRef = useRef<HTMLDivElement>(null);

	const handleDownload = async () => {
		if (!divRef.current) return;

		try {
			const div = divRef.current;
			const canvas = await html2canvas(div, { scale: 1 });
			canvas.toBlob((blob) => {
				if (blob !== null) {
					saveAs(blob, "result.png");
				}
			});
		} catch (error) {
			console.error("Error converting div to image:", error);
		}
	};

	const addItem = (url: string | StaticImageData) => {
		const boundary = boundaryRef.current?.getBoundingClientRect();

		if (boundary) {
			setDragItems([...dragItems,
				{
					x: 250,
					y: 250,
					w: 180,
					h: 180,
					url: url
				}
			])
		}
	}


	return (
		<div className={styles.container}>
			<div ref={divRef} className={styles.leftDiv}>
				<Boundary ref={boundaryRef}>
					{dragItems && dragItems.map((item, index) => {
						return (
							<div
								style={{width: item.w, height: item.h, left: item.x, top: item.y}}
								className={styles.dragBox}
								key={index}
								{...registDragEvent((deltaX, deltaY) => {
									const boundary = boundaryRef.current?.getBoundingClientRect();

									setDragItems(
											update(dragItems, {
												[index]: {
													$merge: {
														x: inrange(
															item.x + deltaX,
															-Math.floor(20 - (item.w / 2) - BOUNDARY_MARGIN),
															Math.floor(580 - (item.w / 2) - BOUNDARY_MARGIN)
														),
														y: inrange(
															item.y + deltaY,
															-Math.floor(55 - (item.h / 2) - BOUNDARY_MARGIN),
															Math.floor(525 - (item.h / 2) - BOUNDARY_MARGIN)
														),
														w: item.w,
														h: item.h,
														url: item.url
												}
											}})
										)
								})}
							>
								<div className={styles.boxDiv}>
									<div className={styles.box} >
										<Image className={styles.image} src={item.url} alt="샹품 이미지" />

									</div>

									{/* 좌상단 */}
									<div
										className={styles.box__leftTop}
										{...registDragEvent((deltaX, deltaY) => {
											setDragItems(
												update(dragItems, {
													[index]: {
														$merge: {
															x: inrange(item.x + deltaX, BOUNDARY_MARGIN, item.x + item.w - MIN_W),
															y: inrange(item.y + deltaY, BOUNDARY_MARGIN, item.y + item.h - MIN_H),
															w: inrange(item.w - deltaX, MIN_W, item.x + item.w - BOUNDARY_MARGIN),
															h: inrange(item.h - deltaY, MIN_H, item.y + item.h - BOUNDARY_MARGIN),
															url: item.url
														}
													}})
											)
										}, true)}
									/>
									{/* 우상단 */}
									<div
										className={styles.box__rightTop}
										{...registDragEvent((deltaX, deltaY) => {
											if (!boundaryRef.current) return;

											const boundary = boundaryRef.current.getBoundingClientRect();

											setDragItems(
												update(dragItems, {
													[index]: {
														$merge: {
															x: item.x,
															y: inrange(item.y + deltaY, BOUNDARY_MARGIN, item.y + item.h - MIN_H),
															w: inrange(item.w + deltaX, MIN_W, boundary.width - item.x - BOUNDARY_MARGIN),
															h: inrange(item.h - deltaY, MIN_H, item.y + item.h - BOUNDARY_MARGIN),
															url: item.url
														}
													}})
											)
										}, true)}
									/>
									{/* 좌하단 */}
									<div
										className={styles.box__leftBottom}
										{...registDragEvent((deltaX, deltaY) => {
											if (!boundaryRef.current) return;

											const boundary = boundaryRef.current.getBoundingClientRect();

											setDragItems(
												update(dragItems, {
													[index]: {
														$merge: {
															x: inrange(item.x + deltaX, BOUNDARY_MARGIN, item.x + item.w - MIN_W),
															y: item.y,
															w: inrange(item.w - deltaX, MIN_W, item.x + item.w - BOUNDARY_MARGIN),
															h: inrange(item.h + deltaY, MIN_H, boundary.height - item.y - BOUNDARY_MARGIN),
															url: item.url
														}
													}})
											)
										}, true)}
									/>
									{/* 우하단 */}
									<div
										className={styles.box__rightBottom}
										{...registDragEvent((deltaX, deltaY) => {
											if (!boundaryRef.current) return;

											const boundary = boundaryRef.current.getBoundingClientRect();

											setDragItems(
												update(dragItems, {
													[index]: {
														$merge: {
															x: item.x,
															y: item.y,
															w: inrange(item.w + deltaX, MIN_W, boundary.width - item.x - BOUNDARY_MARGIN),
															h: inrange(item.h + deltaY, MIN_H, boundary.height - item.y - BOUNDARY_MARGIN),
															url: item.url
														}
													}})
											)
										}, true)}
									/>
									{/* 상단 */}
									<div
										className={styles.box__top}
										{...registDragEvent((_, deltaY) => {
											setDragItems(
												update(dragItems, {
													[index]: {
														$merge: {
															x: item.x,
															y: inrange(item.y + deltaY, BOUNDARY_MARGIN, item.y + item.h - MIN_H),
															w: item.w,
															h: inrange(item.h - deltaY, MIN_H, item.y + item.h - BOUNDARY_MARGIN),
															url: item.url
														}
													}})
											)
										}, true)}
									/>
									{/* 하단 */}
									<div
										className={styles.box__bottom}
										{...registDragEvent((_, deltaY) => {
											if (!boundaryRef.current) return;

											const boundary = boundaryRef.current.getBoundingClientRect();

											setDragItems(
												update(dragItems, {
													[index]: {
														$merge: {
															x: item.x,
															y: item.y,
															w: item.w,
															h: inrange(item.h + deltaY, MIN_H, boundary.height - item.y - BOUNDARY_MARGIN),
															url: item.url
														}
													}})
											)
										}, true)}
									/>
									{/* 우측 */}
									<div
										className={styles.box__right}
										{...registDragEvent((deltaX) => {
											if (!boundaryRef.current) return;

											const boundary = boundaryRef.current.getBoundingClientRect();

											setDragItems(
												update(dragItems, {
													[index]: {
														$merge: {
															x: item.x,
															y: item.y,
															w: inrange(item.w + deltaX, MIN_W, boundary.width - item.x - BOUNDARY_MARGIN),
															h: item.h,
															url: item.url
														}
													}})
											)
										}, true)}
									/>
									{/* 좌측 */}
									<div
										className={styles.box__left}
										{...registDragEvent((deltaX) => {
											setDragItems(
												update(dragItems, {
													[index]: {
														$merge: {
															x: inrange(item.x + deltaX, BOUNDARY_MARGIN, item.x + item.w - MIN_W),
															y: item.y,
															w: inrange(item.w - deltaX, MIN_W, item.x + item.w - BOUNDARY_MARGIN),
															h: item.h,
															url: item.url
														}
													}})
											)
										}, true)}
									/>
								</div>
							</div>
						);
					})}
				</Boundary>
			</div>

			<div className={styles.rightDiv}>
				{
					product.map((url: string | StaticImageData, index: number) => {
						return (
							<div onClick={() => addItem(url)} className={styles.imageDiv} key={index}>
								<Image className={styles.image} src={url} alt="상품 이미지"/>
							</div>
						);
					})
				}
			</div>
		</div>
	);
}

