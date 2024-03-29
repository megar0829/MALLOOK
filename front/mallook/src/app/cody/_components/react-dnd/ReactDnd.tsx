"use client";

import update from 'immutability-helper'
import {FC, useEffect} from 'react'
import { memo, useCallback, useState } from 'react'
import {HTML5Backend, NativeTypes} from 'react-dnd-html5-backend'

import { Box } from './Box'
import {Dustbin, DustbinProps} from './Dustbin'
import {string} from "prop-types";
import {Product} from "@/constants";
import {StaticImageData} from "next/image";

import styles from "./reactDnd.module.css";

interface ImageState {
	url: string | StaticImageData
}

export interface DustbinSpec {
	accepts: string[]
	lastDroppedItem: any
}
export interface BoxSpec {
	name: string
	type: string
}
export interface ContainerState {
	droppedBoxNames: string[]
	dustbins: DustbinSpec[]
	boxes: BoxSpec[]
}

export interface  ProductProps {
	products: Product[]
}

export const Container: FC<ProductProps> = memo(function ReactDnd( { products } ) {
	const [dustbins, setDustbins] = useState<ImageState[]>([]);

	const [images, setImages] = useState<ImageState[]>([])

	useEffect(() => {
		const newImages = products.map(item => ({ url: item.productImg }));
		setImages(newImages);
	}, [products]);

	const [droppedBoxNames, setDroppedBoxNames] = useState<ImageState[]>([])

	function isDropped(url: string | StaticImageData) {
		return droppedBoxNames.indexOf({url}) > -1
	}

	const handleDrop = (item: { url: string | StaticImageData }) => {
		const { url } = item
		setDroppedBoxNames([...droppedBoxNames, {url: url}])
		setDustbins([...dustbins, {url: url}])
	}

	return (
		<div className={styles.container}>
			<div style={{ overflow: 'hidden', clear: 'both' }}>
					<Dustbin
						accept="string"
						lastDroppedItem={dustbins}
						onDrop={(item) => handleDrop(item)}
					/>
			</div>

			<div className={styles.imageContainer} style={{ overflow: 'hidden', clear: 'both' }}>
				{images.map(({ url }, index) => (
					<Box
						url={url}
						type="string"
						isDropped={isDropped(url)}
						key={index}
					/>
				))}
			</div>
		</div>
	)
	})

export default Container
