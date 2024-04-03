
import styles from "./detailProduct.module.css";
import {ProductDetail} from "@/types";
import Image from "next/image";
import {useEffect} from "react";

export default function DetailProduct(props: {productData:ProductDetail}) {

	return (
		<div className={styles.detailProduct__container}>
			<div className={styles.detailProduct__leftDiv}>
				<Image
					className={styles.detailProduct__productImage}
					width={200}
					height={200}
					src={props.productData.image}
					alt="상품 이미지"
					unoptimized={true}
				/>
			</div>

			<div className={styles.detailProduct__rightDiv}>
				<div className={styles.detailProduct__rightDiv__topDiv}>

					<span className={styles.detailProduct__rightDiv__brand}>{props.productData.brandName}</span>
					<hr/>

					<span className={styles.detailProduct__rightDiv__name}>{props.productData.name}</span>
					<span className={styles.detailProduct__rightDiv__price}>{props.productData.price.toLocaleString()} 원</span>
				</div>

				<div className={styles.detailProduct__rightDiv__bottomDiv}>
					<button className={styles.detailProduct__rightDiv__cart}>장바구니</button>
					<button className={styles.detailProduct__rightDiv__purchase}>바로구매</button>
				</div>
			</div>
		</div>
	);
}