"use client";


import {Dispatch, SetStateAction, useEffect, useState} from "react";
import axios from "axios";
import {API_URL} from "@/constants";
import {headers} from "next/headers";
import LoginState from "@/states/login";
import ProductListComponent from "@/app/_components/ProductList";
import {Product} from "@/types";

// const { userToken } = LoginState();

export default function MainProductList() {
	const [productList, setProductList] = useState<Product[]>([]);

	useEffect(() => {
		console.log(productList)

	}, [productList]);

	useEffect(() => {
		if (!productList.length) {
			axios.get(
				`${API_URL}/api/products?primary=아우터&secondary=재킷&size=30`,
				{
					headers: {
						Authorization: "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJtYWxsb29rIiwiZXhwIjoxNzExOTU5OTIyLCJzdWIiOiJmM2I5MzU5Mi04YWM3LTQ3MzQtOGI4MC02YjcwNmRiMDY0M2YiLCJyb2xlcyI6WyJCQVNJQ19VU0VSIl19.miuGMwnxm4LQvwHkuSo1IbV9KxagoNeYenVdJFXMKoc"
						,
					}
				}
			).then((res) => {
				setProductList(res.data.result.content);
			})
		}
	}, []);

	return (
		<>
			{ productList
				&&
				<ProductListComponent productList={productList} />
			}
		</>
	);
}