
import styles from "./detailProduct.module.css";
import {Product} from "@/constants";
import Image from "next/image";

export default function DetailProduct(props: {productData:Product}) {
	return (
		<div className={styles.detailProduct__container}>
			<div className={styles.detailProduct__leftDiv}>
				<Image className={styles.detailProduct__productImage} src={props.productData.productImg} alt="상품 이미지" />
			</div>

			<div className={styles.detailProduct__rightDiv}>
				<div className={styles.detailProduct__rightDiv__topDiv}>
					<span className={styles.detailProduct__rightDiv__name}>{props.productData.name}</span>
					<span className={styles.detailProduct__rightDiv__brand}>{props.productData.brand}</span>
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