// "use client";
//
// import {useRef, useState} from "react";
// import styles from "./testDrag.module.css"
// import {ProductList} from "@/constants";
// import Image, {StaticImageData} from "next/image";
//
// export interface ImageData {
// 	url: string | StaticImageData
// }
//
// export default function TestDrag() {
// 	const products = ProductList.slice(0, 9).map((item) => item.productImg)
//
// 	const dragItem = useRef();
// 	const dragOverItem = useRef();
// 	const [list, setList] = useState<ImageData[]>([]);
//
// 	// 드래그 시작될 때 실행
// 	const dragStart = (e, position) => {
// 		dragItem.current = position;
// 		console.log(e.target.innerHTML);
// 	};
//
// 	// 드래그 중인 대상이 위로 포개졌을 때
// 	const dragEnter = (e, position) => {
// 		dragOverItem.current = position;
// 		console.log(e.target.innerHTML);
// 	};
//
// 	// 드랍 (커서 뗐을 때)
// 	const drop = (e) => {
// 		const newList = [...list];
// 		const dragItemValue = newList[dragItem.current];
// 		newList.splice(dragItem.current, 1);
// 		newList.splice(dragOverItem.current, 0, dragItemValue);
// 		dragItem.current = null;
// 		dragOverItem.current = null;
// 		setList(newList);
// 	}
//
// 	const addProduct = (idx: number) => {
// 		setList([...list, products[idx]])
// 	}
//
// 	return (
// 		<div className={styles.container}>
// 			<div draggable={true} className={styles.drag__container}>
// 				{list &&
// 					list.map((item, index) => {
// 						return (
// 							<div
// 								key={index}
// 								className={styles.dragDiv}
// 								onDragStart={(e) => dragStart(e, index)}
// 								onDragEnter={(e) => dragEnter(e, index)}
// 								onDragEnd={(e) => drop(e)}
// 								onDragOver={(e) => e.preventDefault()}
// 							>
// 								<Image src={item} alt="상품이미지" />
// 							</div>
// 						);
// 					})
// 				}
// 			</div>
// 			<div className={styles.item__container}>
// 				{products.map((item, index) => {
// 					return (
// 						<div onClick={() => addProduct(index)} className={styles.item__imageDiv} key={index}>
// 							<Image className={styles.item__image} src={item} alt="상품 이미지" />
// 						</div>
// 					);
// 				})}
// 			</div>
// 		</div>
// 	);
// }