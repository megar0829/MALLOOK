import Link from "next/link";
import styles from "./cody.module.css";

export const metadata:{title: string} = {
  title: "코디",
};

import Masonry from "@/app/cody/_components/Masonry";


export default function CodyPage() {

  return (
    <div className={styles.container}>
      <Masonry />
    </div>
  );
}