"use client";

import Image from "next/image";
import {API_URL, CodyList} from "@/constants";
import styles from "./detailCody.module.css";
import {useEffect, useState} from "react";
import {Script} from "@/types";
import LoginState from "@/states/login";
import axios from "axios";

export default function DetailCody( props:{scriptId: string} ) {
	const {userToken} = LoginState();

	const [script, setScript] = useState<Script>({
		id: 0,
		name: "",
		heartCount: 0,
		nickname: "",
		imageUrl: ""
	});

	useEffect(() => {
		if (!script.id) {
			if (userToken.accessToken) {
				axios.get(
					`${API_URL}/api/scripts/${props.scriptId}`,
					{
						headers: {
							Authorization: `Bearer ${userToken.accessToken}`
						}
					}
				).then((res) => {
					setScript(res.data.result)
				})
			}
		}
	}, [])

	useEffect(() => {
		console.log(script);
	}, [script]);

	return (
		<div className={styles.cody__container}>
			<div className={styles.cody__leftDiv}>
				<Image
					className={styles.cody__leftDiv__image}
					src={script.imageUrl}
					alt="코디 이미지"
					width={200}
					height={200}
					unoptimized={true}
				/>
			</div>
.
			<div className={styles.cody__rightDiv}>
				<div className={styles.cody__rightDiv__topDiv}>
					{/*<Image className={styles.cody__rightDiv__topDiv__profileImage} src={cody.profileImg} alt="유저 프로필 이미지"/>*/}
					<span className={styles.cody__rightDiv__topDiv__username}>{script.nickname}</span>
				</div>

				<div className={styles.cody__rightDiv__bottomDiv}>
					<span className={styles.cody__rightDiv__bottomDiv__codyName}>{script.name}</span>
					<span className={styles.cody__rightDiv__bottomDiv__span}># 하늘하늘 #간질간질</span>
				</div>
			</div>
		</div>
	);
}