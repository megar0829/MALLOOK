"use client";

import styles from "@/app/(home)/home.module.css";
import {useRouter, useSearchParams} from "next/navigation";
import LoginState, {Token} from "@/states/login";
import {useEffect, useState} from "react";
import ComponentOne from "@/app/(home)/_components/ComponentOne";
import ComponentTwo from "@/app/(home)/_components/ComponentTwo";
import ComponentThree from "@/app/(home)/_components/ComponentThree";
export default function MainComponent() {
	const params = useSearchParams();
	const router = useRouter();
	const { userToken, setUserToken } = LoginState();

	useEffect(() => {
		if (params && params.get("access-token") && params.get("refresh-token")) {
			const tokenData:Token = {
				accessToken: params.get("access-token"),
				refreshToken: params.get("refresh-token"),
			}
			setUserToken(tokenData);
		}
	}, []);

	useEffect(() => {
		console.log(userToken)
		router.push("/")
	}, [userToken]);

	return (
		<div className={styles.container}>
			<ComponentOne />
			<ComponentTwo />
			<ComponentThree />

		</div>
	);
}