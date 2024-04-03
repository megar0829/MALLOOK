"use client";

import {useEffect, useState} from "react";
import styles from './worldCupStartPage.module.css';
import Image, {StaticImageData} from "next/image";
import iconCrown from "@/assets/img/icons/crown.png";
import StartModal from "@/app/worldcup/_components/StartModal";
import {API_URL, ProductList} from "@/constants";
import NextModal from "@/app/worldcup/_components/NextModal";
import {rgba} from "color2k";
import {useRouter} from "next/navigation";
import UseSleep from "@/utils/delay";
import {WorldCupData} from "@/types";
import LoginState from "@/states/login";
import axios from "axios";

interface WorldCupProps {
  url: string | StaticImageData;
  index: number | null;
}

export default function WorldCupStartPage() {
  const {userToken} = LoginState();

  const router = useRouter();

  const [isWorldCup8, setIsWorldCup8] = useState(false);

  const [isWorldCup4, setIsWorldCup4] = useState(false);

  const [isNext1, setIsNext1] = useState(false);
  const [isNext2, setIsNext2] = useState(false);
  const [isNext3, setIsNext3] = useState(false);
  const [isNext4, setIsNext4] = useState(false);
  const [isNext5, setIsNext5] = useState(false);
  const [isNext6, setIsNext6] = useState(false);
  const [isNext7, setIsNext7] = useState(false);

  const [url1, setUrl1] = useState<WorldCupProps>({url: "", index: null});

  const [url21, setUrl21] = useState<WorldCupProps>({url: "", index: null});
  const [url22, setUrl22] = useState<WorldCupProps>({url: "", index: null});

  const [worldCup8List, setWorldCup8List] = useState<WorldCupData[]>([]);

  const [isDone81, setIsDone81] = useState(false);
  const [isDone82, setIsDone82] = useState(false);
  const [isDone83, setIsDone83] = useState(false);
  const [isDone84, setIsDone84] = useState(false);
  const [isDone85, setIsDone85] = useState(false);
  const [isDone86, setIsDone86] = useState(false);
  const [isDone87, setIsDone87] = useState(false);
  const [isDone88, setIsDone88] = useState(false);


  const [worldCup4List, setWorldCup4List] = useState<WorldCupData[]>([]);

  const [worldCup2List, setWorldCup2List] = useState<WorldCupData[]>([]);

  const [worldCupResult, setWorldCupResult] = useState<WorldCupProps>({url: "", index: null});

  useEffect(() => {
    if (!worldCup8List.length) {
      if (userToken.accessToken) {
        axios.get(
          `${API_URL}/api/styles/world-cup`,
          {
            headers: {
              Authorization: `Bearer ${userToken.accessToken}`
            }
          }
        ).then((res) => {
          console.log(res.data)
          setWorldCup8List(res.data.result.content)
        })
      }
    }
  }, []);

  const worldCup8 = () => {
    if (worldCup8List) {
      return (
        <div className={styles.div4}>
          <div className={styles.box}>
            <Image
              className={styles.codyImg}
              src={worldCup8List[0].imageUrl}
              alt="코디 이미지"
              width={200}
              height={200}
              unoptimized={true}
            />
          </div>
          <div className={styles.box}>
            <Image
              className={styles.codyImg}
              src={worldCup8List[1].imageUrl}
              alt="코디 이미지"
              width={200}
              height={200}
              unoptimized={true}
            />
          </div>
          <div className={styles.box}>
            <Image
              className={styles.codyImg}
              src={worldCup8List[2].imageUrl}
              alt="코디 이미지"
              width={200}
              height={200}
              unoptimized={true}
            />
          </div>
          <div className={styles.box}>
            <Image
              className={styles.codyImg}
              src={worldCup8List[3].imageUrl}
              alt="코디 이미지"
              width={200}
              height={200}
              unoptimized={true}
            />
          </div>
          <div className={styles.box}>
            <Image
              className={styles.codyImg}
              src={worldCup8List[4].imageUrl}
              alt="코디 이미지"
              width={200}
              height={200}
              unoptimized={true}
            />
          </div>
          <div className={styles.box}>
            <Image
              className={styles.codyImg}
              src={worldCup8List[5].imageUrl}
              alt="코디 이미지"
              width={200}
              height={200}
              unoptimized={true}
            />
          </div>
          <div className={styles.box}>
            <Image
              className={styles.codyImg}
              src={worldCup8List[6].imageUrl}
              alt="코디 이미지"
              width={200}
              height={200}
              unoptimized={true}
            />
          </div>
          <div className={styles.box}>
            <Image
              className={styles.codyImg}
              src={worldCup8List[7].imageUrl}
              alt="코디 이미지"
              width={200}
              height={200}
              unoptimized={true}
            />
          </div>
        </div>
      );
    }
  }

  const worldCup4 = () => {
    return (
      <div className={styles.div3}>
        <div className={styles.div3__inner}>
          <div className={styles.box}>
            {worldCup4List.length >= 1 &&
              <Image className={styles.codyImg} src={worldCup4List[0].url} alt="4강 1"/>
            }
          </div>
          <div className={styles.box}>
            {worldCup4List.length >= 2 &&
              <Image className={styles.codyImg} src={worldCup4List[1].url} alt="4강 1"/>
            }
          </div>
        </div>
        <div className={styles.div3__inner}>
          <div className={styles.box}>
            {worldCup4List.length >= 3 &&
              <Image className={styles.codyImg} src={worldCup4List[2].url} alt="4강 1"/>
            }
          </div>
          <div className={styles.box}>
            {worldCup4List.length >= 4 &&
              <Image className={styles.codyImg} src={worldCup4List[3].url} alt="4강 1"/>
            }
          </div>
        </div>
      </div>
    );
  }

  const worldCup2 = () => {
    return (
      <div className={styles.div2}>
        <div className={styles.div2__1}>
          <div
            className={styles.box}
            style={{marginBottom: 55}}
          >
            {worldCup2List[0] &&
              <Image className={styles.codyImg} src={worldCup2List[0].url} alt="결승 1"/>
            }
          </div>
        </div>
        <div className={styles.div2__1}>
          <div
            className={styles.box}
            style={{marginBottom: 55}}
          >
            {worldCup2List[1] &&
              <Image className={styles.codyImg} src={worldCup2List[1].url} alt="결승 2"/>
            }
          </div>
        </div>
      </div>
    );
  }

  const worldCup1 = () => {
    return (
      <div className={styles.div1}>
        <div
          className={styles.top__box}
          style={{position: "relative"}}
        >
          <Image className={styles.top__box__crown} src={iconCrown} alt="왕관 이미지"/>
          {worldCupResult.url &&
            <Image
              className={styles.codyImg__result}
              src={worldCupResult.url}
              alt="우승"
            />
          }
        </div>
      </div>
    );
  }

  const goStart = async () => {
    setIsWorldCup8(true);
    await UseSleep(1000);
    setIsNext1(true);
  }

  const goNext2 = async ({url, index}: WorldCupProps) => {
    if (url && index) {
      setWorldCup4List([...worldCup4List, {url: url, index: index}]);
      setIsDone81(true);
      setIsDone82(true);
      setIsNext1(false);
      await UseSleep(1000);
      setIsNext2(true);
    }
  }

  const goNext3 = async ({url, index}: WorldCupProps) => {
    if (url && index) {
      setWorldCup4List([...worldCup4List, {url: url, index: index}]);
      setIsDone83(true);
      setIsDone84(true);
      setIsNext2(false);
      await UseSleep(1000);
      setIsNext3(true);
    }
  }

  const goNext4 = async ({url, index}: WorldCupProps) => {
    if (url && index) {
      setWorldCup4List([...worldCup4List, {url: url, index: index}]);
      setIsDone85(true);
      setIsDone86(true);
      setIsNext3(false);
      await UseSleep(1000);
      setIsNext4(true);
    }
  }

  const goNext41 = async ({url, index}: WorldCupProps) => {
    if (url && index) {
      setWorldCup4List([...worldCup4List, {url: url, index: index}]);
      setIsDone87(true);
      setIsDone88(true);
      setIsNext4(false);
      await UseSleep(1000);
      setIsNext5(true);
    }
  }

  const goNext42 = async ({url, index}: WorldCupProps) => {
    if (url && index) {
      setWorldCup2List([...worldCup2List, {url: url, index: index}]);

      setIsNext5(false);
      await UseSleep(1000);
      setIsNext6(true);
    }
  }
  const goNext21 = async ({url, index}: WorldCupProps) => {
    if (url && index) {
      setWorldCup2List([...worldCup2List, {url: url, index: index}]);

      setIsNext6(false);
      await UseSleep(1000);
      setIsNext7(true);
    }
  }

  const goResult = async ({url, index}: WorldCupProps) => {
    if (url && index) {
      setWorldCupResult({url: url, index: index});
      setIsNext7(false);
      await UseSleep(1000);
      router.push("/worldcup/result")
    }
  }


  return (
    <div className={styles.container}>
      {/*{!isWorldCup8 &&*/}
      {/*  <StartModal goStart={goStart}/>*/}
      {/*}*/}

      {/*{isNext1 &&*/}
      {/*  <NextModal goNext={goNext2} left={worldCup8List[0]}*/}
      {/*             right={worldCup8List[1]}/>*/}
      {/*}*/}

      {/*{isNext2 &&*/}
      {/*  <NextModal goNext={goNext3} left={{url: worldCup8List[2].url, index: worldCup8List[2].index}}*/}
      {/*             right={{url: worldCup8List[3].url, index: worldCup8List[3].index}}/>*/}
      {/*}*/}

      {/*{isNext3 &&*/}
      {/*  <NextModal goNext={goNext4} left={{url: worldCup8List[4].url, index: worldCup8List[4].index}}*/}
      {/*             right={{url: worldCup8List[5].url, index: worldCup8List[5].index}}/>*/}
      {/*}*/}

      {/*{isNext4 &&*/}
      {/*  <NextModal goNext={goNext41} left={{url: worldCup8List[6].url, index: worldCup8List[6].index}}*/}
      {/*             right={{url: worldCup8List[7].url, index: worldCup8List[7].index}}/>*/}
      {/*}*/}

      {/*{isNext5 &&*/}
      {/*  <NextModal goNext={goNext42} left={{url: worldCup4List[0].url, index: worldCup4List[0].index}}*/}
      {/*             right={{url: worldCup4List[1].url, index: worldCup4List[1].index}}/>*/}
      {/*}*/}

      {/*{isNext6 &&*/}
      {/*  <NextModal goNext={goNext21} left={{url: worldCup4List[2].url, index: worldCup4List[2].index}}*/}
      {/*             right={{url: worldCup4List[3].url, index: worldCup4List[3].index}}/>*/}
      {/*}*/}

      {/*{isNext7 &&*/}
      {/*  <NextModal goNext={goResult} left={{url: worldCup2List[0].url, index: worldCup2List[0].index}}*/}
      {/*             right={{url: worldCup2List[1].url, index: worldCup2List[1].index}}/>*/}
      {/*}*/}

      {worldCup1()}

      {worldCup2()}

      {worldCup4()}

      {worldCup8()}

    </div>
  );
}