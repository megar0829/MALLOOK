import Link from "next/link";

export const metadata = {
  title: "월드컵",
};

const iframeComponent = () => {
  return {
    __html:
      `<iframe src="./a.html" width="100%" height="700px"></iframe>`
  }
};

export default async function WorldCupPage() {
  const Post = () => {
    return <div dangerouslySetInnerHTML={{ __html: iframeComponent() }} />;
  };

  return (
    <div>
      {Post()}
    </div>
  );
}