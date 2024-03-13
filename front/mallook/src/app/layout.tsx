// import "../assets/scss/style.scss";
import "../assets/css/global.css";
import { Metadata } from "next";
import Navigation from "@/app/_components/navigation";
import Script from "next/script";

export const metadata :Metadata = {
  title: {
    template: "%s | Mallook",
    default: "Loading..."
  },
  description: 'The clothing store',
};

export default function Layout(
  { children, modal }
  : 
  { children: React.ReactNode, modal: React.ReactNode }
  ) {
  return (
    <html lang="ko">
      <Script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" />
      <body>
        <Navigation />
        {children}
        {modal}
      </body>
    </html>
  )
}
