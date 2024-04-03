import styles from "@/app/(home)/home.module.css";
import Image from "next/image";


export default function ComponentTwo() {
    return (
        <div className={styles.twoContainer}>
            <p id="simpleUsage"></p>
            <div className={styles.twoTextTop}>
                <h1>뭐 입을지 몰룩?</h1>
            </div>
            <div className={styles.twoTextBottom}>
                <h1><span>몰룩 스타일</span>로 변신!</h1>
            </div>
        </div>
    );
}