import styles from "./detailReview.module.css";
import {ProductReview} from "@/types";
import Image from "next/image";

export default function DetailReview(props:{ reviews: ProductReview}) {

	const reviewList = () => {
		return (
			props.reviews.reviewList.map((review, index) => {
				return (
					<div className={styles.review__container} key={index}>
						<div className={styles.review__leftDiv}>
							{review.images.length
								?
								<Image
										className={styles.review__image}
										src={review.images[0]}
										alt="리뷰이미지"
										width={200}
										height={200}
										unoptimized={true}
								/>
								:
								<div className={styles.review__image__blank}>
									<span>이미지가 없습니다.</span>
								</div>
							}
						</div>

						<div className={styles.review__line}></div>

						<div className={styles.review__rightDiv}>
							<div className={styles.review__rightDiv__topDiv}>
								<span>{review.createdAt}</span>
							</div>
							<div className={styles.review__rightDiv__middleDiv}>
								<span>{review.contents}</span>
							</div>
							<div className={styles.review__rightDiv__bottomDiv}>
								{ review.userSize.height && review.userSize.weight
									?
									<div className={styles.review__rightDiv__bottomDiv}>
										<span>신장 : {review.userSize.height}</span>
										<span>몸무게 : {review.userSize.weight}</span>
									</div>
									:
									<div className={styles.review__rightDiv__bottomDiv}>
										<span>사용자 사이즈 정보가 없습니다.</span>
									</div>
								}
							</div>
						</div>
					</div>
				);
			})
		);
	}


	return (
		<div className={styles.reviewList__container}>
			{reviewList()}
		</div>
	);
}