import styles from "./codyCreate.module.css";
import DragAndDraop from "@/app/cody/_components/DragAndDraop";

export const metadata = {
  title: "코디 만들기",
};

export default async function CodyCreatePage() {
  // const products = ProductList.slice(0, 9);

  return (
    <div className={styles.codyCreate__background}>
      <DragAndDraop />
    </div>
  );
}