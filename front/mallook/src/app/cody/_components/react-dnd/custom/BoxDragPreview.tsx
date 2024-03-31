import type { CSSProperties, FC } from 'react'
import { memo, useEffect, useState } from 'react'

import { Box } from './Box'
import Image, {StaticImageData} from "next/image";

import styles from "./dnd.module.css";

export interface BoxDragPreviewProps {
	url: string | StaticImageData
}

export interface BoxDragPreviewState {
	tickTock: any
}

export const BoxDragPreview: FC<BoxDragPreviewProps> = memo(
	function BoxDragPreview({ url }) {
		const [tickTock, setTickTock] = useState(false)

		useEffect(
			function subscribeToIntervalTick() {
				const interval = setInterval(() => setTickTock(!tickTock), 500)
				return () => clearInterval(interval)
			},
			[tickTock],
		)

		return (
			<div>
				<div
					className={styles.imageDiv}
					role={'BoxPreview'}
				>
					<Image className={styles.image} src={url} alt="상품이미지"/>
				</div>
			</div>
		)
	},
)
