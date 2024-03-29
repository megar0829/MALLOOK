"use client";

import type { FC } from 'react'
import { memo } from 'react'
import { useDrag } from 'react-dnd'
import Image, {StaticImageData} from "next/image";

import styles from "./reactDnd.module.css";

export interface ImageProps {
	url: string | StaticImageData
	type: string
	isDropped: boolean
}

export const Box: FC<ImageProps> = memo(function Box({ url, type, isDropped }) {
	const [{ opacity }, drag] = useDrag(
		() => ({
			type,
			item: { url },
			collect: (monitor) => ({
				opacity: monitor.isDragging() ? 0.4 : 1,
			}),
		}),
		[url, type],
	)

	return (
		<div ref={drag} className={styles.imageDiv} style={{ opacity }} data-testid="box">
			{!isDropped &&
				<Image className={styles.image} src={url} alt="상품 이미지" />
			}
		</div>
	)
})
