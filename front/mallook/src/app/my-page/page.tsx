import Link from "next/link";
import styles from "./myPage.module.css";
import Profile from "@/app/my-page/_components/Profile";
import Scripts from "@/app/my-page/_components/Scripts";

export const metadata = {
  title: "마이페이지",
};

export default async function MyPagePage() {

  return (
    <div className={styles.container}>
      <Profile />
      <Scripts/>
    </div>
  );
}