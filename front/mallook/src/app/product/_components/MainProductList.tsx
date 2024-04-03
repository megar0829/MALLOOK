"use client";


import {Dispatch, SetStateAction, useEffect, useState} from "react";
import axios from "axios";
import {API_URL} from "@/constants";
import {headers} from "next/headers";
import LoginState from "@/states/login";
import ProductListComponent from "@/app/_components/ProductList";
import {Product} from "@/types";

// const { userToken } = LoginState();

export default function MainProductList(props: {
	chooseCategory: string,
	chooseDetailCategory: string
}) {
	const [productList, setProductList] = useState<Product[]>([]);

	const {userToken} = LoginState();

	useEffect(() => {
		if (!productList.length) {
			if (!userToken.accessToken) return
			axios.get(
				`${API_URL}/api/products/popular?size=100`,
				{
					headers: {
						Authorization: `Bearer ${userToken.accessToken}`
						,
					}
				}
			).then((res) => {
				console.log(res.data.result.content)
				setProductList(res.data.result.content);
			}).catch((err) => console.log(err));
		}
	}, []);

	useEffect(() => {
		if (!userToken.accessToken) return

		const categoryName = props.chooseCategory;
		const detailCategoryName = props.chooseDetailCategory;
		let apiUrl = '';

		if (detailCategoryName == "top100") {
			apiUrl = `${API_URL}/api/products/popular`;
		} else {
			apiUrl = `${API_URL}/api/products?primary=${categoryName}&secondary=${detailCategoryName}&size=30`;
		}

		axios.get(
			apiUrl,
			{
				headers: {
					Authorization: `Bearer ${userToken.accessToken}`
					,
				}
			}
		).then((res) => {
			console.log(res.data.result.content)
			setProductList(res.data.result.content);
		}).catch((err) => console.log(err));
	}, [props.chooseDetailCategory]);

	return (
		<>
			{ productList
				&&
				<ProductListComponent productList={productList} />
			}
		</>
	);
}