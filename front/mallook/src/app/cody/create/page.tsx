import styles from "./codyCreate.module.css";
import DragAndDraop from "@/app/cody/_components/DragAndDraop";
import Link from 'next/link';

export const metadata = {
  title: "코디 만들기",
};

export default function CodyCreatePage() {
    return (
        <div className={styles.codyCreate__background}>
            <DragAndDraop />
            <div className={styles.completeButtonDiv}>
                <Link href="/cody">
                    <div className={styles.completeButton}>
                        <span>MALLOOK BOOK 생성</span>
                    </div>
                </Link>
            </div>
        </div>
    );
}