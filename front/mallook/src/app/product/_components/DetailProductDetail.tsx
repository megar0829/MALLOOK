"use client";

import parse from "html-react-parser";
import styles from "./detailProductDetail.module.css"
import {useState} from "react";


export default function DetailProductDetail(props: {productHtml: string}) {
	const [isHide, setIsHide] = useState(true);

	const onClickButton = () => {
		setIsHide(!isHide);
	}

	const HtmlComponent = () => {
		return (
			<div
				style={{ height: isHide ? "150dvh" : "", overflowY: isHide ? "hidden" : "visible"}}
				className={styles.detailProductDetail__HTML}
				dangerouslySetInnerHTML={{__html: props.productHtml}}
			>
			</div>
		);
	};

	const buttonText = () => {
		if (isHide) {
			return "상품 정보 더보기 ▼"
		} else {
			return "상품 정보 숨기기 ▲"
		}
	}

	return (
		<div className={styles.detailProductDetail__container}>
			{HtmlComponent()}
			<button onClick={onClickButton} className={styles.detailProductDetail__button}>
				{buttonText()}
			</button>
		</div>
	);
}
