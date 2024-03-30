"use client";
import Link from "next/link";
import styles from "./testDrag.module.css";
import {ProductList} from "@/constants";
import Image from "next/image";
import {useEffect, useRef, useState} from "react";
import Box from "@/app/cody/_components/Box";
import Boundary from "@/app/cody/_components/Boundary";

export const inrange = (v: number, min: number, max: number) => {
	if (v < min) return min;
	if (v > max) return max;
	return v;
};

export default function TestDrag() {
	const [pos, setPos] = useState({ x: 0, y: 0 });

	const boundaryRef = useRef<HTMLDivElement>(null);
	const boxRef = useRef<HTMLDivElement>(null);



	useEffect(() => {
		console.log(pos)
	}, [pos])

	return (
		<div className={styles.background}>
			<Boundary className={styles.container} ref={boundaryRef}>
				<div
					className={styles.drag__component}
					style={{transform: `translateX(${pos.x}px) translateY(${pos.y}px)`}}
					onMouseDown={(e) => {
						const initX = e.pageX;
						const initY = e.pageY;

						const mouseMoveHandler = (e: MouseEvent) => {
							if (boundaryRef.current && boxRef.current) {
								const boundary = boundaryRef.current.getBoundingClientRect();
								const box = boxRef.current.getBoundingClientRect();
								const BOUNDARY_MARGIN = 12;

								const deltaX = e.pageX - initX;
								const deltaY = e.pageY - initY;

								setPos({
									x: inrange(
										pos.x + deltaX,
										Math.floor(-boundary.width / 2 + box.width / 2 + BOUNDARY_MARGIN),
										Math.floor(boundary.width / 2 - box.width / 2 - BOUNDARY_MARGIN),
									),
									y: inrange(
										pos.y + deltaY,
										Math.floor(-boundary.height / 2 + box.height / 2 + BOUNDARY_MARGIN),
										Math.floor(boundary.height / 2 - box.height / 2 - BOUNDARY_MARGIN),
									),
								});
							}
						};
						const mouseUpHandler = (e: MouseEvent) => {
							document.removeEventListener('mousemove', mouseMoveHandler);
						};

						document.addEventListener('mousemove', mouseMoveHandler);
						document.addEventListener('mouseup', mouseUpHandler, { once: true });
					}}
				>
					<Box  ref={boxRef} >

					</Box>
				</div>
			</Boundary>
		</div>
	);
}