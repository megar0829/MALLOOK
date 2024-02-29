import Link from "next/link";

export const metadata = {
  title: `추천 상세`,
};

interface IParams {
  params: {scriptId: string};
}

export default async function ScriptDetailPage({params: {scriptId}} : IParams) {

  return (
    <div>
      script detail page
    </div>
  );
}