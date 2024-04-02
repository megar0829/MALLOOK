import styles from "@/app/(home)/home.module.css";
import Image from "next/image";
import MirrowImg from "@/assets/img/icons/land_mirrow.png";
import BeforeImg from "@/assets/img/icons/land_before.png";
import MallookImg from "@/assets/img/icons/land_mallook.png";

export default function ComponentOne() {
    return (
        <div className={styles.oneTwoThreeContainer}>
            <div className={styles.oneDiv}>
                <div className={styles.oneLeftDiv}>
                    <Image
                        src={MirrowImg}
                        alt="mirrow"
                        style={{
                        }}
                    />
                    <Image
                        src={BeforeImg}
                        alt="before"
                        style={{
                        }}
                    />
                </div>
                <div className={styles.oneRightDiv}>
                    <Image
                        src={MallookImg}
                        alt="mallook"
                        style={{

                        }}
                    />
                </div>
            </div>
        </div>
    );
}