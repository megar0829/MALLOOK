import {StaticImageData} from "next/image";

export interface Product {
	image: string | StaticImageData;
	name: string;
	brandName: string;
	price: number;
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
	point: number;
	exp: number;
	profileImg: string | StaticImageData;
}

export interface ReviewData {
	content: string;
	created_at: string | Date;
	images: string | StaticImageData;
	point: string | null;
	product_option: any [];
	user_size: number [];
}