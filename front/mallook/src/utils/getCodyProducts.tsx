import axios from "axios";
import {API_URL} from "@/constants";
import LoginState from "@/states/login";
import CodyCategoryState, {Category, Product} from "@/states/codyCreate";
import update from "immutability-helper";
import {useState} from "react";


export default async function getCodyProducts() {
	const {userToken} = LoginState();

	const {category, setCategory} = CodyCategoryState();

	if (!userToken.accessToken) {
		return
	}

	const topData: Product[] = await axios.get(
		`${API_URL}/api/styles/mallook-books?primary=상의`,
		{
			headers: {
				Authorization: `Bearer ${userToken.accessToken}`
			}
		}
	).then((res) => res.data.result.content);

	const bottomData: Product[] = await axios.get(
		`${API_URL}/api/styles/mallook-books?primary=하의`,
		{
			headers: {
				Authorization: `Bearer ${userToken.accessToken}`
			}
		}
	).then((res) => res.data.result.content);

	const outerData: Product[] = await axios.get(
		`${API_URL}/api/styles/mallook-books?primary=아우터`,
		{
			headers: {
				Authorization: `Bearer ${userToken.accessToken}`
			}
		}
	).then((res) => res.data.result.content);

	const onePieceData: Product[] = await axios.get(
		`${API_URL}/api/styles/mallook-books?primary=원피스`,
		{
			headers: {
				Authorization: `Bearer ${userToken.accessToken}`
			}
		}
	).then((res) => res.data.result.content);

	const bagData: Product[] = await axios.get(
		`${API_URL}/api/styles/mallook-books?primary=가방`,
		{
			headers: {
				Authorization: `Bearer ${userToken.accessToken}`
			}
		}
	).then((res) => res.data.result.content);

	const shoesData: Product[] = await axios.get(
		`${API_URL}/api/styles/mallook-books?primary=신발`,
		{
			headers: {
				Authorization: `Bearer ${userToken.accessToken}`
			}
		}
	).then((res) => res.data.result.content);

	const hatData: Product[] = await axios.get(
		`${API_URL}/api/styles/mallook-books?primary=모자`,
		{
			headers: {
				Authorization: `Bearer ${userToken.accessToken}`
			}
		}
	).then((res) => res.data.result.content);

	const categoryData: Category = {
		top: topData,
		bottom: bottomData,
		outer: outerData,
		onePiece: onePieceData,
		bag: bagData,
		shoes: shoesData,
		hat: hatData
	}

	setCategory(categoryData);

	return
}