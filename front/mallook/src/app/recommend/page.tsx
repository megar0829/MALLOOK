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

  const [isScripts, setIsScripts] = useState(false);

  const [scriptProducts, setScriptProducts] = useState<ScriptProducts[]>([]);

  const [isScriptProducts, setIsScriptProducts] = useState(false);

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
        console.log("두번째 데이터 완료")
          setScripts([...scripts, ...res.data.result.content])
        }
      ).then(() => {
        console.log(("스크립트 상품 받으러 가기"))
        setIsScripts(true)
      })
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
        console.log("첫번째 데이터 완료")
        setScripts(res.data.result.content)
      }).then(() => {
        console.log("두번째 데이터 받으러 가기")
        getScrips();
      })
    } else {
      console.log("state 에러")
      console.log(scripts)
    }
  }, []);

  useEffect(() => {
    const getScriptProducts = (id: number) => {
      axios.get(
        `${API_URL}/api/scripts/${id}/product-list`,
        {
          headers: {
            Authorization: `Bearer ${userToken.accessToken}`
          }
        }
      ).then((res) => {
        console.log(`상품 추출 ${id}`)
        setScriptProducts([...scriptProducts, { id: id, products: res.data.result}])
      })
    }

    if (isScripts) {
      console.log("=======================")
      console.log(scripts)
      console.log("=======================")

      let lst:ScriptProducts[] = [];

      scripts.map(async (item) => {
        await axios.get(
        `${API_URL}/api/scripts/${item.id}/product-list`,
          {
            headers: {
              Authorization: `Bearer ${userToken.accessToken}`
            }
          }
        ).then((res) => {
          console.log(res.data.result)
          lst.push({id: item.id, products:res.data.result})
        })
      })

      console.log("check")
      console.log(lst)
      setScriptProducts(lst);
      setIsScriptProducts(true);
      console.log("상품 추출 완료")
    }
  }, [isScripts]);

  useEffect(() => {
    console.log("상품 데이터")
    console.log(scriptProducts)
  }, [scriptProducts]);

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

            <div className={styles.rightDiv}>
              { productList &&
                <MainProductList scriptProduct={productList}/>
              }
            </div>
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