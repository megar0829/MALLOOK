"use client";

import Image from "next/image";
import styles from "./resultModal.module.css";
import {Product, Script, WorldCupData} from "@/types";
import {useEffect, useState} from "react";
import LoginState from "@/states/login";
import axios from "axios";
import {API_URL} from "@/constants";
import Link from "next/link";

export default function ResultModal(props: {
	resultScript: Script
}) {
	const {userToken} = LoginState();
	const [productList, setProductList] = useState<Product[]>([]);

	useEffect(() => {
		if (!productList.length) {
			if (userToken.accessToken) {
				axios.get(
					`${API_URL}/api/scripts/${props.resultScript.id}/product-list`,
					{
						headers: {
							Authorization: `Bearer ${userToken.accessToken}`
						}
					}
				).then((res) => {
					setProductList(res.data.result)
				})
			}
		}
	}, []);

	return (
		<div className={styles.background}>
			<div className={styles.container}>
				<div
					className={styles.left__container}
				>
					<Link href={`/recommend/${props.resultScript.id}`} >
						<Image
							className={styles.image}
							src={props.resultScript.imageUrl}
							alt="결과 스크립트 이미지"
							width={200}
							height={200}
							unoptimized={true}
						/>
					</Link>
					<div className={styles.spanDiv}>
						<span className={styles.span}>{props.resultScript.name}</span>
					</div>
				</div>

				<div className={styles.middle__container}>
					<div className={styles.line} />
				</div>

				<div
					className={styles.right__container}
				>
					<div className={styles.right__topDiv}>
						{ productList.length
							?
							productList.slice(0, 3).map((product, index) => {
								return (
									<div key={index} className={styles.right__topDiv__product}>
										<Image
											src={product.image}
											className={styles.right__topDiv__product__image}
											alt="상품 이미지"
											width={200}
											height={200}
											unoptimized={true}
										/>
										<div className={styles.right__topDiv__product__textDiv}>
											<span className={styles.right__topDiv__product__name}>{product.name}</span>
											<span className={styles.right__topDiv__product__brand}>{product.brandName}</span>
											<span className={styles.right__topDiv__product__price}>{product.price.toLocaleString()} 원</span>
										</div>
									</div>
								);
							})
							:
							<div></div>
						}
					</div>

					<div className={styles.right__bottomDiv}>
						<Link href={`/recommend/${props.resultScript.id}`} >
							<span className={styles.right__bottomDiv__span}>이 스크립트로 추천 받기</span>
						</Link>
					</div>
				</div>
			</div>
		</div>
	);
}