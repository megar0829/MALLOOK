import "../styles/global.css";
import { Metadata } from "next";
import Navigation from "../components/navigation"

export const metadata :Metadata = {
  title: {
    template: "%s | Mallook",
    default: "Loading..."
  },
  description: 'The clothing store',
};

export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="ko">
      <body>
        <Navigation />
        {children}
      </body>
    </html>
  )
}
