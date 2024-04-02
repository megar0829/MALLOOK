import {create} from "zustand";

export interface Product {
	productId: string;
	imgUrl: string;
}

export interface Category {
	top: Product[],
	bottom: Product[],
	outer: Product[],
	onePiece: Product[],
	bag: Product[],
	shoes: Product[],
	hat: Product[]
}

export interface CategoryState {
	category: Category;
	setCategory: (categoryData: Category) => void;
	deleteCategory: () => void;
}

const CodyCategoryState = create<CategoryState>((set) => ({
	category: {
		top: [],
		bottom: [],
		outer: [],
		onePiece: [],
		bag: [],
		shoes: [],
		hat: [],
	},
	setCategory: (categoryData: Category) => {set( { category: categoryData } ) },
	deleteCategory: () => {set ( { category: {
			top: [],
			bottom: [],
			outer: [],
			onePiece: [],
			bag: [],
			shoes: [],
			hat: [],
		} } )}
}))

export default CodyCategoryState