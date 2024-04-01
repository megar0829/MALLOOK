import Link from "next/link";
import styles from "./cody.module.css";

import icon from "@/assets/img/icons/logo_sm.png";

export const metadata:{title: string} = {
  title: "코디",
};

import Masonry from "@/app/cody/_components/Masonry";
import Image from "next/image";


export default function CodyPage() {

  return (
    <div className={styles.container}>
      <Masonry />
      <Link href={"/cody/create"}>
        <div className={styles.iconDiv}>
          <Image className={styles.iconImage} src={icon} alt="옷걸이 아이콘" />
        </div>
      </Link>
    </div>
  );
}