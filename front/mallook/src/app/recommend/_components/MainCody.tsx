import styles from "./mainCody.module.css";
import Image from "next/image";

import {CodyData} from "@/constants";
import Link from "next/link";

export default function MainCody(props: { cody: CodyData, codyId: number } ) {
  return (
    <Link href={`/recommend/${props.codyId}`}>
      <div className={styles.cody}>
        <Image className={styles.codyImg} src={props.cody.codyImg} alt="코디 이미지" />
        <div className={styles.codyImg__cover}></div>

        <div className={styles.cody__topDiv}>
          <div className={styles.cody__topDiv__profileDiv}>
            <Image className={styles.cody__topDiv__profileImg} src={props.cody.profileImg} alt="프로필 이미지" />
          </div>
          <span className={styles.cody__topDiv__username}>
            {props.cody.username}
          </span>
        </div>

        <div className={styles.cody__bottomDiv}>
          <span className={styles.cody__bottomDiv__codyName}>{props.cody.codyName}</span>
        </div>
      </div>
    </Link>
  );

}