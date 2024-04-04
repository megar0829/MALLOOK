"use client";

import Modal from "@/app/@modal/_components/modal";
import { useRouter } from "next/navigation";
import {useCallback, useState} from "react";
import styles from "./choose-keyword.module.css";
import Image from "next/image";

const keywordList = [
  "원피스", "목걸이", "나이키", "가방", "코트", "스니커즈", "티셔츠", "청바지", "스카프", "액세서리",
  "드레스", "시계", "모자", "선글라스", "귀걸이", "반지", "블라우스", "스커트", "백팩", "부츠",
  "커플링", "코트", "모자", "양말", "신발", "자켓", "팔찌", "루이비통", "가디건", "반바지",
  "후드티", "스카프", "니트", "카디건", "머플러", "캐주얼", "스니커즈", "티셔츠", "트레이닝복", "실내화",
  "운동화", "양산", "워치", "코튼", "가죽", "라운지웨어", "청바지", "플랫슈즈", "패딩", "가방",
  "백팩", "캐리어", "비치웨어", "비키니", "스커트", "패션", "의류", "액세서리", "모자", "코트",
  "티셔츠", "워치", "부츠", "선글라스", "가디건", "자켓", "반지", "드레스", "신발", "가방",
  "스카프", "반바지", "스니커즈", "백팩", "모자", "팔찌", "캐주얼", "블라우스", "스커트", "워치",
  "후드티", "트레이닝복", "머플러", "가죽", "스니커즈", "니트", "실내화", "양산", "가죽가방", "비치웨어",
  "패션", "비키니", "패딩", "코튼", "라운지웨어", "플랫슈즈", "코트", "가방", "액세서리", "티셔츠",
  "청바지", "자켓", "신발", "모자", "드레스", "반지", "워치", "선글라스", "가디건", "스카프",
  "스니커즈", "백팩", "모자", "팔찌", "캐주얼", "스커트", "블라우스", "가죽", "양산", "실내화",
  "스니커즈", "워치", "니트", "후드티", "트레이닝복", "머플러", "반바지", "패션", "비치웨어", "가방",
  "코튼", "라운지웨어", "플랫슈즈", "비키니", "모자", "코트", "티셔츠", "액세서리", "청바지", "자켓",
  "신발", "반지", "가디건", "드레스", "선글라스", "스카프", "스니커즈", "백팩", "모자", "팔찌",
  "스커트", "캐주얼", "블라우스", "워치", "부츠", "후드티", "트레이닝복", "머플러", "가죽", "스니커즈",
  "니트", "양산", "실내화", "패션", "비치웨어", "비키니", "코튼", "라운지웨어", "플랫슈즈", "패딩",
  "가방", "액세서리", "티셔츠", "청바지", "자켓", "신발", "모자", "반지", "워치", "가디건",
  "드레스", "선글라스", "스카프", "스니커즈", "백팩", "팔찌", "캐주얼", "모자", "스커트", "블라우스",
  "워치", "부츠", "후드티", "트레이닝복", "머플러", "가죽", "스니커즈", "니트", "양산", "실내화",
  "패션", "비치웨어", "비키니", "코튼", "라운지웨어", "플랫슈즈", "패딩", "가방", "액세서리", "티셔츠",
  "청바지", "자켓", "신발", "모자", "반지", "가디건", "드레스", "선글라스", "스카프"
];

export default function LoginModal() {

  const router = useRouter();

  const [chooseList, setChooseList] = useState<number[]>([]);

  const onClose = useCallback(() => {
    router.back(); // 라우터 뒤로가기 기능을 이용
  }, [router]);

  const goRecommend = () => {
    router.replace("/recommend");
  }

  return (
    <Modal>
      <div className={styles.container}>
        <div className={styles.textDiv}>
          <span className={styles.span1}>키워드 선택</span>
          <span className={styles.span2}>마음에 드는 단어를 골라 보세요</span>
        </div>

        <div className={styles.keywordListDiv}>
          {keywordList.map((word, index) => {
            return (
              <div
                className={styles.keywordDiv}
                key={index}
                onClick={() => setChooseList([...chooseList, index])}
                style={{backgroundColor: chooseList.includes(index) ? "red" : ""}}
              >
                <span className={styles.keyword}>
                  {word}
                </span>
              </div>
            );
          })}
        </div>

        <button 
          className={styles.button}
          onClick={goRecommend}
        >
          완료
        </button>
      </div>
    </Modal>
  );
}