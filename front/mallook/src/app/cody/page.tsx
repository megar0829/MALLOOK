import Link from "next/link";

export const metadata = {
  title: "코디",
};

import {CodyBookList} from "@/constants";

export default async function CodyPage() {

  const post = () => {
    return (
      <div>

      </div>
    );
  }

  const postList = () => {
    return (
      CodyBookList.map((codyBook, index) => {
        return (
          <div key={index}>

          </div>
        );
      })
    );
  }

  return (
    <div>
      cody page
    </div>
  );
}