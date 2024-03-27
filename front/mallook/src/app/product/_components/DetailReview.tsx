import styles from "./detailReview.module.css";
import {ReviewData} from "@/constants";
import Image from "next/image";


function Review(props:{ review: ReviewData}) {
	return (
		<div>
			<div>
				<Image src={props.review.images} alt="리뷰 이미지" />
			</div>
			<div>

			</div>
		</div>
	);
}

export default function DetailReview() {
	const review = {
		"content": "품질이 생각보다 좋고,\n만족스러워서 가성비가 좋아요!!",
		"created_at": "1709521906",
		"images": "https://image.brandi.me/user/2023/04/12/2745488802_1681270328_L.jpg",
		"point": null,
		"product_option": [
			[
				"플리츠 긴팔 셔츠_베이지",
				"2XL",
				"단품구매"
			]
		],
		"user_size": [
			174,
			0
		]
	}


	return (
		<div>
			{Review({review:review})}
		</div>
	);
}