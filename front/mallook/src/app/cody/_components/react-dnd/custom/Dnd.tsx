import type { FC } from 'react'
import { useCallback, useState } from 'react'

import { CustomDragLayer } from './CustomDragLayer'
import {Container} from "./Container";
import styles from "./dnd.module.css";
import {ProductList} from "@/constants";
import Image, {StaticImageData} from "next/image";

export interface ItemData {
	top: number;
	left: number;
	url: string | StaticImageData
}

export const Dnd: FC = () => {
	const products = ProductList.slice(0, 9).map((item) => item.productImg)

	const [dragItems, setDragItems] = useState<ItemData[]>([]);


	// 드롭 후 아이템이 범위 내에 있는지 확인하는 변수
	const [snapToGridAfterDrop, setSnapToGridAfterDrop] = useState(false)
	// 드래깅 중 아이템이 범위 내에 있는지 확인하는 변수
	const [snapToGridWhileDragging, setSnapToGridWhileDragging] = useState(false)

	const handleSnapToGridAfterDropChange = useCallback(() => {
		setSnapToGridAfterDrop(!snapToGridAfterDrop)
	}, [snapToGridAfterDrop])

	const handleSnapToGridWhileDraggingChange = useCallback(() => {
		setSnapToGridWhileDragging(!snapToGridWhileDragging)
	}, [snapToGridWhileDragging])

	const addItem = (idx: number) => {
		setDragItems([...dragItems,
			{
				top: 100,
				left: 100,
				url: products[idx]
			}
		])
	}


	return (
		<div className={styles.container}>
			<div className={styles.left__container}>
				<Container snapToGrid={snapToGridAfterDrop} dragItems={dragItems} />
				{/*드래깅 중 아이템을 보여주는 컴포넌트 */}
				<CustomDragLayer snapToGrid={snapToGridWhileDragging} dragItems={dragItems} />
			</div>
			<div className={styles.right__container}>
				{products.map((url, index) => {
					return (
						<div onClick={() => addItem(index)} className={styles.imageDiv} key={index}>
							<Image className={styles.image} src={url} alt="상품 이미지" />
						</div>
					);
				})}
			</div>
		</div>
	)
}

export default Dnd