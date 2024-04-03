import styles from "./mainCody.module.css";
import Image from "next/image";

import {CodyData, Script} from "@/types";
import Link from "next/link";
import GetProfileImage from "@/utils/GetProfileImage";

export default function MainCody(props: { script: Script } ) {
  return (
    <Link href={`/recommend/${props.script.id}`}>
      <div className={styles.cody}>
        <Image
          className={styles.codyImg}
          src={props.script.imageUrl}
          alt="코디 이미지"
          width={200}
          height={200}
          unoptimized={true}
        />
        <div className={styles.codyImg__cover}></div>

        <div className={styles.cody__topDiv}>
          <div className={styles.cody__topDiv__profileDiv}>
            {/*<Image className={styles.cody__topDiv__profileImg} src={GetProfileImage(props.script.memberGrade)} alt="프로필 이미지" />*/}
          </div>
          <span className={styles.cody__topDiv__username}>
            {props.script.nickname}
          </span>
        </div>

        <div className={styles.cody__bottomDiv}>
          <span className={styles.cody__bottomDiv__codyName}>{props.script.name}</span>
        </div>
      </div>
    </Link>
  );

}