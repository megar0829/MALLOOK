"use client";
import Link from "next/link";
import styles from "./codyCreate.module.css";
import {ProductList} from "@/constants";
import Image from "next/image";
import {useRef, useState} from "react";
import TestDrag from "@/app/cody/_components/TestDrag";

// export const metadata = {
//   title: "코디 만들기",
// };

export default function CodyCreatePage() {
  const products = ProductList.slice(0, 9);

  // const productList = () => {
  //   return (
  //     products.map((product, index) => {
  //       return (
  //         <div
  //           className={styles.codyCreate__rightDiv__imageDiv}
  //           key={index}
  //           ref={dragComponentRef}
  //           draggable={true}
  //         >
  //           <Image src={product.productImg} alt="상품이미지" className={styles.codyCreate__rightDiv__image} />
  //         </div>
  //       );
  //     })
  //   )
  // }

  const containerRef = useRef<HTMLDivElement>(null);
  const dragComponentRef = useRef<HTMLDivElement>(null);
  const [originPos, setOriginPos] = useState({ x: 0, y: 0 });
  const [clientPos, setClientPos] = useState({ x: 0, y: 0 });
  const [pos, setPos] = useState({ left: 0, top: 0 });

  const onDragStartHandler = (e: any) => {
    const blankCanvas: any = document.createElement('canvas')
    blankCanvas.classList.add("canvas");
    e.dataTransfer?.setDragImage(blankCanvas, 0, 0);
    document.body?.appendChild(blankCanvas); // 투명 캔버스를 생성하여 글로벌 아이콘 제거
    e.dataTransfer.effectAllowed = "move"; // 크롬의그린 +아이콘 제거
    console.log("==============================");
    console.log("Drag Start");

    const originPosTemp = { ...originPos };
    originPosTemp["x"] = e.target.offsetLeft;
    originPosTemp["y"] = e.target.offsetTop;
    console.log("originPosTemp", originPosTemp);
    setOriginPos(originPosTemp); //드래그 시작할때 드래그 전 위치값을 저장


    const clientPosTemp = { ...clientPos };
    clientPosTemp["x"] = e.clientX;
    clientPosTemp["y"] = e.clientY;
    console.log("clientPosTemp", clientPosTemp);
    setClientPos(clientPosTemp);
    console.log("==============================");

  };

  const onDragHandler = (e: any) => {
    const PosTemp = { ...pos };
    PosTemp["left"] = e.target.offsetLeft + e.clientX - clientPos.x;
    PosTemp["top"] = e.target.offsetTop + e.clientY - clientPos.y;
    setPos(PosTemp);
    console.log("==============================");
    console.log("On Drag");
    console.log("PosTemp", PosTemp);

    const clientPosTemp = { ...clientPos };
    clientPosTemp["x"] = e.clientX;
    clientPosTemp["y"] = e.clientY;
    setClientPos(clientPosTemp);
    console.log("clientPosTemp", clientPosTemp);
    console.log("==============================");
  };

  const onDragOverHandler = (e: any) => {
    e.preventDefault(); // 드래그시에 플라잉백하는 고스트이미지를 제거한다
    console.log("==============================");
    console.log("Drag Over")
    console.log("==============================");
  };

  const isInsideDragArea = (e: any) => {
    return true
  }

  const onDragEndHandler = (e: any) => {
    console.log("==============================");
    console.log("Drag End")
    console.log(e)
    if (isInsideDragArea(e)) {
      const posTemp = { ...pos };
      posTemp["left"] = originPos.x;
      posTemp["top"] = originPos.y;
      setPos(posTemp);
    }
    // 캔버스 제거
    const canvases = document.getElementsByClassName("canvas");
    for (let i = 0; i < canvases.length; i++) {
      let canvas = canvases[i];
      canvas.parentNode?.removeChild(canvas);
    }
    // 캔버스로 인해 발생한 스크롤 방지 어트리뷰트 제거
    document.body.removeAttribute("style");
    console.log("==============================");
  };


  return (
    // <div className={styles.codyCreate__background}>
    //   <div className={styles.codyCreate__container}>
    //     <div className={styles.codyCreate__leftDiv}>
    //       <div ref={containerRef} className={styles.codyCreate__leftDiv__container}>
    //
    //       </div>
    //     </div>
    //
    //     <div className={styles.codyCreate__rightDiv}>
    //       <div className={styles.codyCreate__rightDiv__imageList}>
    //         <div
    //           className={styles.codyCreate__rightDiv__imageDiv}
    //           ref={dragComponentRef}
    //           draggable={true}
    //           onDragStart={(e) => onDragStartHandler(e)}
    //           onDrag={(e) => onDragHandler(e)}
    //           onDragOver={(e) => onDragOverHandler(e)}
    //           onDragEnd={(e) => onDragEndHandler(e)}
    //         >
    //           <Image src={ProductList[0].productImg} alt="상품이미지" className={styles.codyCreate__rightDiv__image} />
    //         </div>
    //       </div>
    //     </div>
    //   </div>
    // </div>
    <>
      <TestDrag />
    </>
  );
}