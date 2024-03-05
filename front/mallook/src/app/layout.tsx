// import "../assets/scss/style.scss";
import "../styles/global.css";
import { Metadata } from "next";
import Navigation from "../components/navigation"
import { Providers } from "./providers";

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
      <body>
        <Providers>
          <Navigation />
          {children}
          {modal}
        </Providers>
      </body>
    </html>
  )
}
