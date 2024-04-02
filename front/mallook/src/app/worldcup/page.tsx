
import Link from "next/link";
import parse from "html-react-parser";
import styles from "./worldcup.module.css";

import WorldCupStartPage from "@/app/worldcup/start/page";


export const metadata = {
  title: "월드컵",
};



export default async function WorldCupPage() {

  return (
    <div className={styles.container}>
      <WorldCupStartPage />
    </div>
  );
}
