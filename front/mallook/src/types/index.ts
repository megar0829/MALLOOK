import {StaticImageData} from "next/image";
import * as worker_threads from "worker_threads";

export interface Product {
	brandName: string;
	code: string | null;
	detailImages: string[];
	fee: string | null;
	gender: string;
	id: string;
	image: string;
	mainCategory: string;
	name: string;
	price: string;
	size: string | null;
	subCategory: string;
	tags: string;
	url: string;
}

export interface ProductDetail {
	brandName: string;
	code: string | null;
	color: string[];
	detailHtml: string;
	detailImages: string[];
	fee: string | null;
	gender: string;
	id: string;
	image: string;
	keywords: string[];
	mainCategory: string;
	name: string;
	price: number;
	review: ProductReview;
	size: string[];
	subCategory: string | null;
	tags: string | null
	url: string;
}

export interface ProductReview {
	count: number;
	averagePoint: number;
	reviewList: Review[];
}

export interface Review {
	contents: string;
	createdAt: string;
	images: string[];
	point: number | null;
	productOption: {
		color: string;
		size: string | null;
	}[]
	userSize : {
		height: string;
		weight: string;
	}
}

export interface Script {
	id: number;
	name: string;
	heartCount: number;
	nickname: string;
	imageUrl: string;
}

export interface ScriptProducts {
	id: number;
	products: Product[];
}

export interface SampleProduct {
	productImg: string | StaticImageData;
	name: string;
	brand: string;
	price: number;
}

export interface CodyData {
	codyImg: string | StaticImageData;
	codyName: string;
	profileImg: string | StaticImageData;
	username: string;
	productLeft: SampleProduct[];
	productRight: SampleProduct[];
}

export interface ProductListProps {
	productLeft: Product[];
	productRight: Product[];
}

export interface CodyBookData {
	profileImg: string | StaticImageData;
	username: string;
	content: string;
	likeCnt: number;
	codyImg: string | StaticImageData;
}

export interface DetailCategoryData {
	categoryName: string,
	categoryUrl: string | StaticImageData
}

export interface MainCategoryData {
	name: string,
	url: string | StaticImageData ,
	detailCategory: DetailCategoryData[]
}

export interface ProfileData {
	nickname: string;
	birth: string;
	gender: string;
	phone: string;
	point: number;
	exp: number;
	city: string;
	district: string;
	address: string;
	zipcode: string;
}

export interface ProfileSampleData {
	nickname: string;
	nicknameTag: string;
	birth: string;
	gender: string;
	phone: string;
	grade: string;
	expRange: number[];
	level: string;
	point: number;
	exp: number;
	address: {
		city: string;
		district: string;
		address: string;
		zipcode: string;
	}
	memberCoupon: number;
	coupon: number;
	cartProduct: number;
	orders: number;
}

export interface ReviewData {
	content: string;
	created_at: string | Date;
	images: string | StaticImageData;
	point: string | null;
	product_option: any [];
	user_size: number [];
}