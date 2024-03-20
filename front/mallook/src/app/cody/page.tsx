import Link from "next/link";
import styles from "cody.module.css";
// import {Metadata} from "next";
//
// export const metadata:{title: string} = {
//   title: "코디",
// };

import {CodyBookList} from "@/constants";
import TestCarousel from "@/app/cody/_components/TestCarousel";

export default async function CodyPage() {

  const post = () => {
    return (
      <div>

      </div>
    );
  }
  return (
    <>
      <TestCarousel />
    </>
  );
}