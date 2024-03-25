"use client";

import Modal from "@/app/@modal/_components/modal";
import { useRouter } from "next/navigation";
import { useCallback } from "react";
import styles from "./kakao.module.css";
import Image from "next/image";

export default function KakaoLoginPage() {

	const router = useRouter();

	const onClose = useCallback(() => {
		router.back(); // 라우터 뒤로가기 기능을 이용
	}, [router]);

	const kakaoLogin = () => {
		router.push("https://j10a606.p.ssafy.io/oauth2/authorization/kakao")
	}

	return (
		<Modal>
			<div className={styles.container}>
				<iframe
					src="https://j10a606.p.ssafy.io/oauth2/authorization/kakao"
					width="100%"
					height="500px"
					style={{border: "none"}}
					allowFullScreen
					>
				</iframe>
			</div>
		</Modal>
)
	;
}