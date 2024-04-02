"use client";

import { useEffect, useRef, useState } from 'react';
import Boundary from "./Boundary";
import styles from "./dragAndDrop.module.css";
import Image, {StaticImageData} from "next/image";
import {API_URL, ProductList} from "@/constants";
import registDragEvent from "@/utils/registDragEvent";
import update from "immutability-helper";
import {inrange} from "@/utils";
import html2canvas from "html2canvas";
import {saveAs} from "file-saver";
import getCodyProducts from "@/utils/getCodyProducts";
import LoginState from "@/states/login";
import axios from "axios";
import CodyCategoryState, {Category, Product} from "@/states/codyCreate"

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

const getProductData = async () => {
	const {userToken} = LoginState();

}


export default function DragAndDraop() {
	const [codyProducts, setCodyProducts] = useState<Product[]>([]);

	const [backgroundColor, setBackgroundColor] = useState("white");

	const [dragItems, setDragItems] = useState<ItemData[]>([]);

	const boundaryRef = useRef<HTMLDivElement>(null);
	const divRef = useRef<HTMLDivElement>(null);

	const {userToken} = LoginState();

	const [topData, setTopData] = useState<Product[]>([]);
	const [bottomData, setBottomData] = useState<Product[]>([]);
	const [outerData, setOuterData] = useState<Product[]>([]);
	const [onePieceData, setOnePieceData] = useState<Product[]>([]);
	const [bagData, setBagData] = useState<Product[]>([]);
	const [shoesData, setShoesData] = useState<Product[]>([]);
	const [hatData, setHatData] = useState<Product[]>([]);

	useEffect(() => {
		if (userToken.accessToken) {
			if (!topData.length) {
				axios.get(
					`${API_URL}/api/styles/mallook-books?primary=상의`,
					{
						headers: {
							Authorization: `Bearer ${userToken.accessToken}`
						}
					}
				).then((res) => setTopData(res.data.result.content));
			}

			if (!bottomData.length) {
				axios.get(
					`${API_URL}/api/styles/mallook-books?primary=하의`,
					{
						headers: {
							Authorization: `Bearer ${userToken.accessToken}`
						}
					}
				).then((res) => setBottomData(res.data.result.content));
			}

			if (!outerData.length) {
				axios.get(
					`${API_URL}/api/styles/mallook-books?primary=아우터`,
					{
						headers: {
							Authorization: `Bearer ${userToken.accessToken}`
						}
					}
				).then((res) => setOuterData(res.data.result.content));
			}

			if (!onePieceData.length) {
				axios.get(
					`${API_URL}/api/styles/mallook-books?primary=원피스`,
					{
						headers: {
							Authorization: `Bearer ${userToken.accessToken}`
						}
					}
				).then((res) => setOnePieceData(res.data.result.content));
			}

			if (!bagData.length) {
				axios.get(
					`${API_URL}/api/styles/mallook-books?primary=가방`,
					{
						headers: {
							Authorization: `Bearer ${userToken.accessToken}`
						}
					}
				).then((res) => setBagData(res.data.result.content));
			}

			if (!shoesData.length) {
				axios.get(
					`${API_URL}/api/styles/mallook-books?primary=신발`,
					{
						headers: {
							Authorization: `Bearer ${userToken.accessToken}`
						}
					}
				).then((res) => setShoesData(res.data.result.content));
			}

			if (!hatData.length) {
				axios.get(
					`${API_URL}/api/styles/mallook-books?primary=모자`,
					{
						headers: {
							Authorization: `Bearer ${userToken.accessToken}`
						}
					}
				).then((res) => setHatData(res.data.result.content));
			}
		}
	}, []);



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
			<div ref={divRef} style={{ backgroundColor: backgroundColor }} className={styles.leftDiv}>
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
										<Image className={styles.image} width={200} height={200} src={item.url} alt="샹품 이미지" />

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
				<div className={styles.rightDiv__topDiv}>
					<div className={styles.rightDiv__category}>
						<div onClick={() => setCodyProducts(topData)} className={styles.category__div}>
							<span className={styles.category__span}>상의</span>
						</div>
						<div onClick={() => setCodyProducts(bottomData)} className={styles.category__div}>
							<span className={styles.category__span}>하의</span>
						</div>
						<div onClick={() => setCodyProducts(outerData)} className={styles.category__div}>
							<span className={styles.category__span}>아우터</span>
						</div>
						<div onClick={() => setCodyProducts(onePieceData)} className={styles.category__div}>
							<span className={styles.category__span}>원피스</span>
						</div>
						<div onClick={() => setCodyProducts(bagData)} className={styles.category__div}>
							<span className={styles.category__span}>가방</span>
						</div>
						<div onClick={() => setCodyProducts(shoesData)} className={styles.category__div}>
							<span className={styles.category__span}>신발</span>
						</div>
						<div onClick={() => setCodyProducts(hatData)} className={styles.category__div}>
							<span className={styles.category__span}>모자</span>
						</div>
					</div>
				</div>
				<div className={styles.rightDiv__bottomDiv}>
					<div className={styles.color__container}>
						<div onClick={() => setBackgroundColor("#E5E7EB")} style={{backgroundColor: "#E5E7EB"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#D4C4B7")} style={{backgroundColor: "#D4C4B7"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#BAC1CE")} style={{backgroundColor: "#BAC1CE"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#7D9292")} style={{backgroundColor: "#7D9292"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#E0D5B9")} style={{backgroundColor: "#E0D5B9"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#B7A98E")} style={{backgroundColor: "#B7A98E"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#8293AB")} style={{backgroundColor: "#8293AB"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#AEC1CD")} style={{backgroundColor: "#AEC1CD"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#B4B6C3")} style={{backgroundColor: "#B4B6C3"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#ACB5B6")} style={{backgroundColor: "#ACB5B6"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#BBBCB7")} style={{backgroundColor: "#BBBCB7"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#BAC1CE")} style={{backgroundColor: "#BAC1CE"}} className={styles.color__chip}></div>
						<div onClick={() => setBackgroundColor("#B7A98E")} style={{backgroundColor: "#B7A98E"}} className={styles.color__chip}></div>
					</div>
					<div className={styles.product__container}>
						{
							codyProducts.length &&
								codyProducts.map((product: Product, index: number) => {
								return (
									<div onClick={() => addItem(product.imgUrl)} className={styles.imageDiv} key={index}>
										<Image className={styles.image} width={200} height={200} src={product.imgUrl} alt="상품 이미지"/>
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

