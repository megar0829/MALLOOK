import styles from "./productList.module.css";
import Image from "next/image";
import Link from "next/link";
import React from "react";
import {Product} from "@/types";
import {inherits} from "node:util";

export default function ProductComponent(props: { productData: Product, id: string }) {
	return (
		<Link href={`/product/${props.id}`} >
			<div className={styles.product}>
				<Image
					className={styles.product__image}
					width={200}
					height={200}
					src={props.productData.image }
					alt="상품 이미지"
					unoptimized={true}
					style={{
						width: "auto",
						height: "50%",
					}}
				/>
				<div className={styles.product__textDiv}>
					<span className={styles.product__brand}>{props.productData.brandName}</span>
					<span className={styles.product__name}>{props.productData.name}</span>
					<span className={styles.product__price}>{props.productData.price.toLocaleString()} 원</span>
				</div>
			</div>
		</Link>
	)
}