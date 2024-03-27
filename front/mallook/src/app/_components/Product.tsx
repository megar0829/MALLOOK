import styles from "./productList.module.css";
import Image from "next/image";
import Link from "next/link";
import React from "react";
import {Product} from "@/constants";

export default function ProductComponent(props: { productData: Product, id: number }) {
	return (
		<Link href={`/product/${props.id}`} >
			<div className={styles.product}>
				<Image className={styles.product__image} src={props.productData.productImg} alt="상품 이미지"/>
				<div className={styles.product__textDiv}>
					<span className={styles.product__price}>{props.productData.price}</span>
					<span className={styles.product__name}>{props.productData.name}</span>
					<span className={styles.product__brand}>{props.productData.brand}</span>
				</div>
			</div>
		</Link>
	)
}