"use client";

import Link from "next/link";
import {usePathname, useSearchParams} from "next/navigation";
import {useEffect, useState} from "react";
import Image from "next/image";
import styles from "./navigation.module.css";

import imgLogo from "@/assets/img/icons/logo.png";
import iconSearch from "@/assets/img/icons/search.png";
import iconCart from "@/assets/img/icons/cart.png";
import iconProfile from "@/assets/img/icons/profile.png";

import LoginState, {Token} from "@/states/login";

export default function Navigation() {
  const path = usePathname();
  const [count, setCount] = useState(0);

  // console.log(path);
  return (
    <nav>
      <div className={styles.container}>
        <div className={styles.leftContainer}>
          <div className={styles.leftContainerLogo}>
            <Link href="/">
              <Image 
                src={imgLogo} 
                alt="Logo Image" 
                style={{
                  width: "130px",
                  height:"70px"
                }} 
                />  
            </Link>
          </div>
          <ul className={styles.leftContainerUl}>
            <li>
              <Link href="/recommend">추천</Link>
            </li>
            <li>
              <Link href="/product">상품</Link>
            </li>
            <li>
              <Link href="/cody">몰룩북</Link>
            </li>
          </ul>
        </div>

        <div className={styles.rightContainer}>
          <ul className={styles.rightContainerUl}>
            <li>
              <Link href="https://j10a606.p.ssafy.io/oauth2/authorization/kakao">
                <Image
                  src={iconSearch}
                  alt="search Image"
                  style={{
                    width: "30px",
                    height:"30px"
                  }}
                />
              </Link>
              {/*<Link href="/login">*/}
              {/*  <Image*/}
              {/*    src={iconSearch}*/}
              {/*    alt="search Image"*/}
              {/*    style={{*/}
              {/*      width: "30px",*/}
              {/*      height:"30px"*/}
              {/*    }}*/}
              {/*  />*/}
              {/*</Link>*/}
            </li>
            <li>
              <Link href="/cart">
                <Image
                  src={iconCart}
                  alt="cart Image"
                  style={{
                    width: "30px",
                    height:"30px"
                  }}
                />
              </Link>
            </li>
            <li>
              <Link href="/my-page">
                <Image
                  src={iconProfile}
                  alt="profile Image"
                  style={{
                    width: "40px",
                    height:"40px"
                  }}
                />
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}