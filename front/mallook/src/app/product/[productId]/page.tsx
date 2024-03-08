import Link from "next/link";

export const metadata = {
  title: "상품 상세",
};

interface IParams {
  params: {productId: string};
}

export default async function ProductDetailPage({params: {productId}} : IParams) {

  return (
    <div>
      product detail page
    </div>
  );
}