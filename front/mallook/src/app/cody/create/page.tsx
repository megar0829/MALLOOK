import styles from "./codyCreate.module.css";
import {ProductList} from "@/constants";
import {DndProvider} from "react-dnd";
import {HTML5Backend} from "react-dnd-html5-backend";
import Dnd from "@/app/cody/_components/react-dnd/custom/Dnd";
import DragAndDraop from "@/app/cody/_components/DragAndDraop";

// export const metadata = {
//   title: "코디 만들기",
// };

export default async function CodyCreatePage() {
  // const products = ProductList.slice(0, 9);

  return (
    <div className={styles.codyCreate__background}>
      {/*<DndProvider backend={HTML5Backend}>*/}
      {/*  <Dnd />*/}
      {/*</DndProvider>*/}
      <DragAndDraop />
    </div>
  );
}