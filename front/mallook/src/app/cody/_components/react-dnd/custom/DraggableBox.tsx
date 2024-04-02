import type { CSSProperties, FC } from 'react'
import { memo, useEffect } from 'react'
import type { DragSourceMonitor } from 'react-dnd'
import { useDrag } from 'react-dnd'
import { getEmptyImage } from 'react-dnd-html5-backend'

import { ItemTypes } from './ItemTypes'
import Image, {StaticImageData} from "next/image";
import styles from "@/app/cody/_components/react-dnd/custom/dnd.module.css";

function getStyles(
	left: number,
	top: number,
	isDragging: boolean,
): CSSProperties {
	const transform = `translate3d(${left}px, ${top}px, 0)`
	return {
		position: 'absolute',
		transform,
		WebkitTransform: transform,
		// IE fallback: hide the real node using CSS when dragging
		// because IE will ignore our custom "empty image" drag preview.
		opacity: isDragging ? 0 : 1,
		height: isDragging ? 0 : '',
	}
}

export interface DraggableBoxProps {
	id: number
	url: string | StaticImageData
	left: number
	top: number
}

export const DraggableBox: FC<DraggableBoxProps> = memo(function DraggableBox(
	props,
) {
	const { id, url, left, top } = props
	const [{ isDragging }, drag, preview] = useDrag(
		() => ({
			type: ItemTypes.BOX,
			item: { id, left, top, url },
			collect: (monitor: DragSourceMonitor) => ({
				isDragging: monitor.isDragging(),
			}),
		}),
		[id, left, top, url],
	)

	useEffect(() => {
		preview(getEmptyImage(), { captureDraggingState: true })
	}, [])

	return (
		<div
			ref={drag}
			style={getStyles(left, top, isDragging)}
			role="DraggableBox"
		>
			{url &&
				<div
					className={styles.imageDiv}
					role={'Box'}
				>
					<Image className={styles.image} src={url} alt="상품이미지"/>
					<div className={styles.box__leftTop} />
				</div>
			}
		</div>
	)
})
