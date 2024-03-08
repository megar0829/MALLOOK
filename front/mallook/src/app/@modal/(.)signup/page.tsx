"use client";

import Modal from "@/components/modal/modal";
import { useRouter } from "next/navigation";
import { useCallback, useState } from "react";
import styles from "../../../styles/signup.module.css";
import Image from "next/image";
import Complete from "./complete";

declare global {
  interface Window {
    daum: any;
  }
}

interface IAddr {
  address: string;
  zonecode: string;
}

export default function SignUpModal() {

  const router = useRouter();

  const [isComplete, setIsComplete] = useState(false);

  const onClose = useCallback(() => {
    router.back(); // 라우터 뒤로가기 기능을 이용
  }, [router]);

  const onClickAddr = () => {
    new window.daum.Postcode({
      oncomplete: function (data: IAddr) {
        (document.getElementById("addr") as HTMLInputElement).value =
          data.address;
          // data.address + "(" + data.zonecode + ")";
        // (document.getElementById("zipNo") as HTMLInputElement).value =
        //   data.zonecode;
        document.getElementById("addrDetail")?.focus();
      },
    }).open();
  }; 

  const goComplete = () => {
    // 회원가입 로직 성공 시
    setIsComplete(!isComplete);
  };

  const signUpForm = () => {
    if (!isComplete) {
      return (
        <div className={styles.container}>
          <div className={styles.topDiv}>
            <span className={styles.topText}>회원가입</span>
          </div>

          <div className={styles.nicknameDiv}>
            <div className={styles.nicknameLabelDiv}>
              <label className={styles.nicknameLabel}>닉네임</label>
              <span className={styles.nicknameSpan}>입력하지 않으면 자동으로 생성돼요</span>
            </div>
            <div className={styles.nicknameInputDiv}>
              <input className={styles.nicknameInput} type="text" placeholder="큐티레드윤정" />
              <input className={styles.nicknameButton} type="submit" value="중복검사" />
            </div>
          </div>

          <div className={styles.birthGenderDiv}>
            <div className={styles.birthDiv}>
              <label className={styles.birthLabel}>생년월일</label>
              <input className={styles.birthInput} type="text" placeholder="6자리 입력" />
            </div>
            <div className={styles.genderDiv}>
              <label className={styles.genderLabel}>성별</label>
              <div className={styles.genderChooseDiv}>
                <div className={styles.genderInputDiv}>
                  <input className={styles.genderInput} type="radio" />
                  <label className={styles.genderRadioLabel} htmlFor="">남자</label>
                </div>
                <div className={styles.genderInputDiv}>
                  <input className={styles.genderInput} type="radio" />
                  <label className={styles.genderRadioLabel} htmlFor="">여자</label>
                </div>
              </div>
            </div>
          </div>
      
          <div className={styles.phoneDiv}>
            <label className={styles.phoneLabel} htmlFor="">전화번호</label>
            <input className={styles.phoneInput} type="number" placeholder="000 - 0000 - 0000" />
          </div>

          <div className={styles.addressDiv}>
            <label className={styles.addressLabel1} htmlFor="">주소</label>
            <div className={styles.addressDiv1}>
              <input 
                className={styles.addressInput1}
                id="addr"
                type="text"
                readOnly
                onClick={onClickAddr}
                placeholder="우편번호 검색"
              />
              <button className={styles.addressButton} onClick={onClickAddr}>검색</button>
            </div>

            {/* <div className={styles.addressDiv2}>
              <label className={styles.addressLabel2} htmlFor="">우편번호</label>
              <input 
                className={styles.addressInput2}
                id="zipNo"
                type="text"
                readOnly
              />
            </div> */}

            <div className={styles.addressDiv3}>
              <input 
                className={styles.addressInput3}
                id="addrDetail"
                type="text"
                placeholder="상세 주소 입력"
              />
            </div>
          </div>

          <button 
            className={styles.signUpButton}
            onClick={goComplete}
          >
            다음
          </button>
        </div>
      );
    } else {
      return (
        <div>
          <Complete />
        </div>
      );
    }
  }

  return (
    <Modal>
      {signUpForm()}
    </Modal>
  );
}