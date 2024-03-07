"use client";

import Modal from "@/components/modal/modal";
import { useRouter } from "next/navigation";
import { useCallback, useState } from "react";
import styles from "../../../styles/signup-complete.module.css";
import Image from "next/image";
import iconCheck from "../../../../public/images/check.png";

export default function Complete() {

  const router = useRouter();
  
  const goWorldcup = () => {
    router.push("/worldcup")
  }
  
  const onClose = () => {
    router.back(); // 라우터 뒤로가기 기능을 이용
  };

  const goKeyword = async () => {
    router.push("/choose-keyword");
  }

  return (
    <div className={styles.container}>
      <div className={styles.div1}>
        <Image className={styles.img} src={iconCheck} alt="check image" />
      </div>

      <span className={styles.span1}>회원가입 완료</span>

      <span className={styles.span2}>더 자세한 코디 추천을 받고 싶다면?</span>

      <button 
        className={styles.button1}
        onClick={goWorldcup}
      >
        월드컵 하러 가기
      </button>

      <button 
        className={styles.button2}
        onClick={goKeyword}
      >
        다음에 할래요 (키워드 선택하러 가기)
      </button>
    </div>
  );
}