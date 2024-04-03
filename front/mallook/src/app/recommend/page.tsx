"use client";

import Link from "next/link";
import styles from "./recommend.module.css";


import MainCody from "@/app/recommend/_components/MainCody";
import MainProductList from "@/app/recommend/_components/MainProductList";



import {API_URL, CodyList} from "@/constants";
import {useEffect, useState} from "react";
import LoginState from "@/states/login";
import axios from "axios";
import {Product, Script, ScriptProducts} from "@/types";
import UseSleep from "@/utils/delay";
import update from "immutability-helper";

export default function RecommendPage() {
  const {userToken} = LoginState();
  const [scripts, setScripts] = useState<Script[]>([]);
  const [scriptProducts, setScriptProducts] = useState<ScriptProducts[]>([]);


  useEffect(() => {
    const getScrips = () => {
      axios.get(
        `${API_URL}/api/scripts/all`,
        {
          headers: {
            Authorization: `Bearer ${userToken.accessToken}`
          }
        }
      ).then((res) => {
        setScripts([...scripts, ...res.data.result.content])
        console.log(res.data.range.content)}
      )
    }

    if (scripts && !scripts.length) {
      if (!userToken.accessToken) {
        console.log("토큰 에러")
        return
      }
      console.log("시작")
      axios.get(
        `${API_URL}/api/scripts`,
        {
          headers: {
            Authorization: `Bearer ${userToken.accessToken}`
          }
        }
      ).then((res) => {
        setScripts(res.data.result.content)
      }).then(() => {
        getScrips();
      })
    } else {
      console.log("state 에러")
      console.log(scripts)
    }
  }, []);

  const codyList = () => {
    return (
      scripts.length && scripts.map((script, index) => {
        const productList: ScriptProducts = scriptProducts.filter((item) => item.id == script.id)[0];

        return (
          <div className={styles.cody__container} key={index}>
            <div className={styles.leftDiv}>
              { script &&
                <MainCody script={script} />
              }
            </div>

            {/*<div className={styles.rightDiv}>*/}
            {/*  { productList &&*/}
            {/*    <MainProductList scriptProduct={productList}/>*/}
            {/*  }*/}
            {/*</div>*/}
          </div>
        );
      })
    );
  }

  return (
    <div className={styles.container}>
      {codyList()}
    </div>
  );
}