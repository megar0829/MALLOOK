"use client";

import styles from './detailProductList.module.css';
import {ProductList} from "@/constants";
import Image from "next/image";

export default function DetailProductList(props:{codyId: string}) {

	const productList = () => {
		return (
			ProductList.map((product, index) => {
				return (
					<div className={styles.product} key={index}>
						<Image className={styles.product__image} src={product.productImg} alt="상품 이미지"/>
						<div className={styles.product__textDiv}>
							<span className={styles.product__price}>{product.price}</span>
							<span className={styles.product__name}>{product.name}</span>
							<span className={styles.product__brand}>{product.brand}</span>
						</div>
					</div>
				);
			})
		);
	}

	return(
		<div className={styles.productList__container}>
			{productList()}
		</div>
	);
}