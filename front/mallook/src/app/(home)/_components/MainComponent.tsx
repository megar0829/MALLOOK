"use client";

import styles from "@/app/(home)/home.module.css";
import LeftDiv from "@/app/(home)/_components/LeftDiv";
import RightDiv from "@/app/(home)/_components/RightDiv";
import {useRouter, useSearchParams} from "next/navigation";
import LoginState, {Token} from "@/states/login";
import {useEffect} from "react";
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
			<LeftDiv />
			<RightDiv />
		</div>
	);
}