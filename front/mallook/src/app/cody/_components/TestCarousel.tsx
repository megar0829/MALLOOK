"use client";

import useEmblaCarousel from "embla-carousel-react";
import Autoplay from "embla-carousel-autoplay";
import {useCallback, useEffect} from "react";
import styles from "../cody.module.css";


export default function TestCarousel() {
	const [emblaRef, emblaApi] = useEmblaCarousel({ loop: false })

	useEffect(() => {
		if (emblaApi) {
			console.log(emblaApi.slideNodes()) // Access API
		}
	}, [emblaApi])

	const onClickLeftButton = useCallback(() => {
		if (emblaApi) emblaApi.scrollPrev()
	}, [emblaApi])

	const onClickRightButton = useCallback(() => {
		if (emblaApi) emblaApi.scrollNext()
	}, [emblaApi])

	return (
		<div className={styles.embla} ref={emblaRef}>
			<div className={styles.embla__container}>
				<div className={styles.embla__slide}>Slide 1</div>
				<div className={styles.embla__slide}>Slide 2</div>
				<div className={styles.embla__slide}>Slide 3</div>
			</div>
			<button onClick={onClickLeftButton}>left</button>
			<br />
			<button onClick={onClickRightButton}>right</button>
		</div>
	);
}