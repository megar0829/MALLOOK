import Image from "next/image";
import LoadingIcon from "@/assets/img/icons/loading_icon.png";
import LoadingText from "@/assets/img/icons/loading_text.png";


export default function Loading() {
  return (
      <div>
        <Image src={LoadingIcon} alt="LoadingIcon"/>
        <Image src={LoadingText} alt="LoadingText"/>
      </div>
  )
}