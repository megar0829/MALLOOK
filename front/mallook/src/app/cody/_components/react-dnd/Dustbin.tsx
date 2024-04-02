"use client";

import type { CSSProperties, FC } from 'react'
import { memo } from 'react'
import { useDrop } from 'react-dnd'
import styles from "./reactDnd.module.css"
import Image, {StaticImageData} from "next/image";


export interface DustbinProps {
	accept: string
	lastDroppedItem?: {url: string | StaticImageData}[]
	onDrop: (item: any) => void
}

export const Dustbin: FC<DustbinProps> = memo(function Dustbin({
	                                                               accept,
	                                                               lastDroppedItem,
	                                                               onDrop,
                                                               }) {
	const [{ isOver, canDrop }, drop] = useDrop({
		accept,
		drop: onDrop,
		collect: (monitor) => ({
			isOver: monitor.isOver(),
			canDrop: monitor.canDrop(),
		}),
	})

	const isActive = isOver && canDrop
	let backgroundColor = '#222'
	if (isActive) {
		backgroundColor = 'darkgreen'
	} else if (canDrop) {
		backgroundColor = 'darkkhaki'
	}

	const droppedImages = () => {
		if (lastDroppedItem) {
			return (
				lastDroppedItem.map((item, index) => {
					return (
						<div className={styles.imageDiv} key={index}>
							<Image className={styles.image} src={item.url} alt={`${index}`} />
						</div>
					);
				})
			)
		}
	}

	console.log(lastDroppedItem)

	return (
		<div ref={drop} className={styles.dragContainer} style={{ backgroundColor }} data-testid="dustbin">
			{droppedImages()}
		</div>
	)
})
