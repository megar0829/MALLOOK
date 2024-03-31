import update from 'immutability-helper'
import {CSSProperties, FC, useEffect} from 'react'
import { useCallback, useState } from 'react'
import { useDrop } from 'react-dnd'

import { DraggableBox } from './DraggableBox'
import type { DragItem } from './interface'
import { ItemTypes } from './ItemTypes'
import { snapToGrid as doSnapToGrid } from './snapToGrid'

import styles from "./dnd.module.css";
import {StaticImageData} from "next/image";
import {ItemData} from "@/app/cody/_components/react-dnd/custom/Dnd";
import {Simulate} from "react-dom/test-utils";
import drag = Simulate.drag;
export interface ContainerProps {
	snapToGrid: boolean;
	dragItems: ItemData[];
}

export const Container: FC<ContainerProps> = ({ snapToGrid, dragItems }) => {

	const [images, setImages] = useState<ItemData[]>(dragItems);

	useEffect(() => {
		setImages(dragItems)
	}, [dragItems])

	const moveBox = useCallback(
		(id: number, left: number, top: number) => {
			setImages(
				update(images, {
					[id]: {
						$merge: {left, top,},
					}
				}),
			)
		},
		[images],
	)

	const [, drop] = useDrop(
		() => ({
			accept: ItemTypes.BOX,
			drop(item: DragItem, monitor) {
				const delta = monitor.getDifferenceFromInitialOffset() as {
					x: number
					y: number
				}
				console.log(item)
				let left = Math.round(item.left + delta.x)
				let top = Math.round(item.top + delta.y)
				if (snapToGrid) {
					;[left, top] = doSnapToGrid(left, top)
				}

				moveBox(item.id, left, top)
				return undefined
			},
		}),
		[moveBox],
	)

	return (
		<div ref={drop} className={styles.drag__container}>
			{images.map((item, index ) => (
				<DraggableBox
					key={index}
					id={index}
					{...(images[index] as { top: number; left: number; url: string | StaticImageData })}
				/>
			))}
		</div>
	)
}

