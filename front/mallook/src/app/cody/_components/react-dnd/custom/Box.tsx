import type { CSSProperties, FC } from 'react'
import { memo } from 'react'
import Image, {StaticImageData} from "next/image";
import styles from "./dnd.module.css";


export interface BoxProps {
	url: string | StaticImageData
	yellow?: boolean
	preview?: boolean
}

export const Box: FC<BoxProps> = memo(function Box({ url, yellow, preview }) {
	const backgroundColor = yellow ? 'yellow' : 'white'
	return (
		<div
			style={{ backgroundColor }}
			className={styles.imageDiv}
			role={preview ? 'BoxPreview' : 'Box'}
		>
			<Image className={styles.image} src={url} alt="상품이미지" />
		</div>
	)
})
