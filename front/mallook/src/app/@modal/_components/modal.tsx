"use client";
import React, {
  useCallback, useRef,
} from "react";
import { useRouter } from "next/navigation";
import styles from "./modal.module.css";

export interface ModalProps {
  children: React.ReactNode;
}

export default function Modal(
  { children }: ModalProps) {

  const router = useRouter();
  const clickedRef = useRef<EventTarget>();

  // 닫기 기능
  const onClose = useCallback(() => {
    router.back(); // 라우터 뒤로가기 기능을 이용
  }, [router]);

  function handleClickClose(e: React.MouseEvent<HTMLElement>) {
    if (clickedRef.current) {
      clickedRef.current = undefined;
      return;
    }

    e.stopPropagation();
    onClose();
  }

  return (
    <div
      className={styles.modal__outer}
      onMouseUp={handleClickClose}
    >
        <div
          className={styles.modal__inner}
          onMouseDown={(e) => clickedRef.current = e.target}
          onMouseUp={(e) => clickedRef.current = e.target}
        >
          {children}
        </div>
    </div>
  );
};