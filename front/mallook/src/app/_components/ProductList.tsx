import React from 'react'
import styles from "./productList.module.css";

import Image from "next/image";

import {ProductList} from "@/constants";
import Link from "next/link";
import ProductComponent from "./Product";

export default function ProductListComponent() {

	const productList = () => {
		return (
			ProductList.map((product, index) => {
				return (
					<ProductComponent productData={product} key={index} id={index} />
				);
			})
		);
	}

	return (
		<div className={styles.productList__container}>
			{productList()}
		</div>
	)
}