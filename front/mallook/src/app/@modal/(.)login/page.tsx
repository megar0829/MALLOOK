"use client";

import Modal from "@/components/modal/modal";
import { useRouter } from "next/navigation";
import { useCallback } from "react";
import styles from "../../../styles/login.module.css";
import Image from "next/image";

import imgLogo from "../../../../public/images/logo.png";
import iconGoogle from "../../../../public/images/google.png";
import iconKakao from "../../../../public/images/kakao.png";
import iconNaver from "../../../../public/images/naver.png";


export default function LoginModal() {

  const router = useRouter();

  const onClose = useCallback(() => {
    router.back(); // 라우터 뒤로가기 기능을 이용
  }, [router]);

  return (
    <Modal>
      <div className={styles.container}>
        <div className={styles.logoDiv}>
          <Image className={styles.logoImg} src={imgLogo} alt="로고" />
        </div>

        <div className={styles.textDiv}>
          <span className={styles.text}>패알못 커몬요 로그인해라</span>
        </div>

        <div className={styles.socialDiv}>
          <button className={styles.kakaoDiv}>
            <Image className={styles.kakaoImg} src={iconKakao} alt="카카오 로그인"/>
          </button>
          <button className={styles.naverDiv}>
            <Image className={styles.naverImg} src={iconNaver} alt="네이버 로그인"/>
          </button>
          <button className={styles.googleDiv}>
            <Image className={styles.googleImg} src={iconGoogle} alt="구글 로그인"/>
          </button>
        </div>
      </div>
    </Modal>
  );
}