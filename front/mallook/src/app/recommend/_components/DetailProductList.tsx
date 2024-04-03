"use client";

import ProductListComponent from "@/app/_components/ProductList";
import {useEffect, useState} from "react";
import {Product} from "@/types";
import LoginState from "@/states/login";
import axios from "axios";
import {API_URL} from "@/constants";

export default function DetailProductList(props: {scriptId: string}) {
	const {userToken} = LoginState();
	const [scriptProductList, setScriptProductList] = useState<Product[]>([]);

	useEffect(() => {
		if (!scriptProductList.length) {
			if (userToken.accessToken) {
				axios.get(
					`${API_URL}/api/scripts/${props.scriptId}/product-detail?size=100`,
					{
						headers: {
							Authorization: `Bearer ${userToken.accessToken}`
						}
					}
				).then((res) => {
					setScriptProductList(res.data.result.content)
				})
			}
		}
	}, []);

	return (
		scriptProductList &&
			<ProductListComponent productList={scriptProductList} />
	);
}