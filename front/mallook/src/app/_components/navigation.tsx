"use client";

import Link from "next/link";
import {usePathname, useSearchParams} from "next/navigation";
import {useEffect, useState} from "react";
import Image from "next/image";
import styles from "./navigation.module.css";

import imgLogo from "@/assets/img/icons/logo_lg.png";
import { IoCartOutline } from "react-icons/io5";
import { IoIosSearch } from "react-icons/io";
import { AiOutlineUser } from "react-icons/ai";

import LoginState, {Token} from "@/states/login";

export default function Navigation() {
  const path = usePathname();
  const [count, setCount] = useState(0);

  // console.log(path);
  return (
    <nav>
      <div className={styles.container}>
        <div className={styles.topContainer}>
          <div className={styles.topContainerLogo}>
            <Link href="/">
              <Image
                  src={imgLogo}
                  alt="Logo Image"
              />
            </Link>
          </div>
          <ul className={styles.rightContainer}>
            <li>
              <Link href="https://j10a606.p.ssafy.io/oauth2/authorization/kakao">
                <IoIosSearch className={styles.rightContainerIcon}/>
              </Link>
              {/*<Link href="/signup">*/}
              {/*  <IoIosSearch className={styles.rightContainerIcon}/>*/}
              {/*</Link>*/}
            </li>
            <li>
              <Link href="/choose-keyword">
                <IoCartOutline className={styles.rightContainerIcon}/>
              </Link>
            </li>
            <li>
              <Link href="/my-page">
                <AiOutlineUser className={styles.rightContainerIcon}/>
              </Link>
            </li>
          </ul>
        </div>
        <ul className={styles.bottomContainer}>
          <li>
            <Link href="/recommend">Recommend</Link>
          </li>
          <li>
            <Link href="/product">Category</Link>
          </li>
          <li>
            <Link href="/cody">Mallookbook</Link>
          </li>
        </ul>
      </div>
    </nav>
  );
}