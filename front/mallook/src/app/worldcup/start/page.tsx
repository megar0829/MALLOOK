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
import {Script, WorldCupData} from "@/types";
import LoginState from "@/states/login";
import axios from "axios";
import ResultModal from "@/app/worldcup/_components/ResultModal";
import LoadingModal from "@/app/worldcup/_components/LoadingModal";

interface WorldCupProps {
  url: string | StaticImageData;
  index: number | null;
}

export default function WorldCupStartPage() {
  const {userToken} = LoginState();

  const router = useRouter();

  const [isWorldCup8, setIsWorldCup8] = useState(false);

  const [isNext1, setIsNext1] = useState(false);
  const [isNext2, setIsNext2] = useState(false);
  const [isNext3, setIsNext3] = useState(false);
  const [isNext4, setIsNext4] = useState(false);
  const [isNext5, setIsNext5] = useState(false);
  const [isNext6, setIsNext6] = useState(false);
  const [isNext7, setIsNext7] = useState(false);

  const [isLoading, setIsLoading] = useState(false);
  const [isResult, setIsResult] = useState(false);
  const [isCreate, setIsCreate] = useState(false);

  const [resultScript, setResultScript] = useState<Script>({
    id: 0,
    name: "",
    heartCount: 0,
    nickname: "",
    imageUrl: "",
  });


  const [worldCup8List, setWorldCup8List] = useState<WorldCupData[]>([]);

  const [isDone81, setIsDone81] = useState(false);
  const [isDone82, setIsDone82] = useState(false);
  const [isDone83, setIsDone83] = useState(false);
  const [isDone84, setIsDone84] = useState(false);
  const [isDone85, setIsDone85] = useState(false);
  const [isDone86, setIsDone86] = useState(false);
  const [isDone87, setIsDone87] = useState(false);
  const [isDone88, setIsDone88] = useState(false);

  const [isDone41, setIsDone41] = useState(false);
  const [isDone42, setIsDone42] = useState(false);
  const [isDone43, setIsDone43] = useState(false);
  const [isDone44, setIsDone44] = useState(false);

  const [isDone21, setIsDone21] = useState(false);
  const [isDone22, setIsDone22] = useState(false);


  const [worldCup4List, setWorldCup4List] = useState<WorldCupData[]>([]);

  const [worldCup2List, setWorldCup2List] = useState<WorldCupData[]>([]);

  const [worldCupResult, setWorldCupResult] = useState<WorldCupData>({
    id: 0,
    name: "",
    heartCount: 0,
    memberNickname: "",
    imageUrl: "",
    keywordList: []
  });

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
          setWorldCup8List(res.data.result)
          console.log(res.data.result)
        })
      }
    }
  }, []);

  const worldCup8 = () => {
    if (worldCup8List.length) {
      return (
        <div className={styles.div4}>
          <div className={styles.box}>
            {
              isDone81 &&
              <div className={styles.boxCover}></div>
            }
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
            {
              isDone82 &&
              <div className={styles.boxCover}></div>
            }
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
            {
              isDone83 &&
              <div className={styles.boxCover}></div>
            }
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
            {
              isDone84 &&
              <div className={styles.boxCover}></div>
            }
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
            {
              isDone85 &&
              <div className={styles.boxCover}></div>
            }
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
            {
              isDone86 &&
              <div className={styles.boxCover}></div>
            }
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
            {
              isDone87 &&
              <div className={styles.boxCover}></div>
            }
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
            {
              isDone88 &&
              <div className={styles.boxCover}></div>
            }
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
            {
              isDone41 &&
              <div className={styles.boxCover}></div>
            }
            {worldCup4List.length >= 1 &&
              <Image
                className={styles.codyImg}
                src={worldCup4List[0].imageUrl}
                alt="4강 1"
                width={200}
                height={200}
                unoptimized={true}
              />
            }
          </div>
          <div className={styles.box}>
            {
              isDone42 &&
              <div className={styles.boxCover}></div>
            }
            {worldCup4List.length >= 2 &&
              <Image
                className={styles.codyImg}
                src={worldCup4List[1].imageUrl}
                alt="4강 2"
                width={200}
                height={200}
                unoptimized={true}
              />
            }
          </div>
        </div>
        <div className={styles.div3__inner}>
          <div className={styles.box}>
            {
              isDone43 &&
              <div className={styles.boxCover}></div>
            }
            {worldCup4List.length >= 3 &&
              <Image
                className={styles.codyImg}
                src={worldCup4List[2].imageUrl}
                alt="4강 3"
                width={200}
                height={200}
                unoptimized={true}
              />
            }
          </div>
          <div className={styles.box}>
            {
              isDone44 &&
              <div className={styles.boxCover}></div>
            }
            {worldCup4List.length >= 4 &&
              <Image
                className={styles.codyImg}
                src={worldCup4List[3].imageUrl}
                alt="4강 4"
                width={200}
                height={200}
                unoptimized={true}
              />
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
            {
              isDone21 &&
              <div className={styles.boxCover}></div>
            }
            {worldCup2List[0] &&
              <Image
                className={styles.codyImg}
                src={worldCup2List[0].imageUrl}
                alt="결승 1"
                width={200}
                height={200}
                unoptimized={true}
              />
            }
          </div>
        </div>
        <div className={styles.div2__1}>
          <div
            className={styles.box}
            style={{marginBottom: 55}}
          >
            {
              isDone22 &&
              <div className={styles.boxCover}></div>
            }
            {worldCup2List[1] &&
              <Image
                className={styles.codyImg}
                src={worldCup2List[1].imageUrl}
                alt="결승 2"
                width={200}
                height={200}
                unoptimized={true}
              />
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
          {worldCupResult.id
            ?
            <Image
              className={styles.codyImg__result}
              src={worldCupResult.imageUrl}
              alt="우승"
              width={200}
              height={200}
              unoptimized={true}
            />
            :
            <div></div>
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

  const goNext2 = async (data: WorldCupData) => {
    if (data.id) {
      setWorldCup4List([...worldCup4List, data]);
      setIsDone81(true);
      setIsDone82(true);
      setIsNext1(false);
      await UseSleep(1000);
      setIsNext2(true);
    }
  }

  const goNext3 = async (data: WorldCupData) => {
    if (data.id) {
      setWorldCup4List([...worldCup4List, data]);
      setIsDone83(true);
      setIsDone84(true);
      setIsNext2(false);
      await UseSleep(1000);
      setIsNext3(true);
    }
  }

  const goNext4 = async (data: WorldCupData) => {
    if (data.id) {
      setWorldCup4List([...worldCup4List, data]);
      setIsDone85(true);
      setIsDone86(true);
      setIsNext3(false);
      await UseSleep(1000);
      setIsNext4(true);
    }
  }

  const goNext41 = async (data: WorldCupData) => {
    if (data.id) {
      setWorldCup4List([...worldCup4List, data]);
      setIsDone87(true);
      setIsDone88(true);
      setIsNext4(false);
      await UseSleep(1000);
      setIsNext5(true);
    }
  }

  const goNext42 = async (data: WorldCupData) => {
    if (data.id) {
      setWorldCup2List([...worldCup2List, data]);
      setIsDone41(true);
      setIsDone42(true);
      setIsNext5(false);
      await UseSleep(1000);
      setIsNext6(true);
    }
  }
  const goNext21 = async (data: WorldCupData) => {
    if (data.id) {
      setWorldCup2List([...worldCup2List, data]);
      setIsDone43(true);
      setIsDone44(true);
      setIsNext6(false);
      await UseSleep(1000);
      setIsNext7(true);
    }
  }

  const getResult = () => {
    console.log(worldCupResult.keywordList)
    axios.post(
      `${API_URL}/api/scripts`,
      {keywordsList: worldCupResult.keywordList},
      {
        headers: {
          Authorization: `Bearer ${userToken.accessToken}`,
          "Content-Type": "application/json"
        }
      }
    ).then((res) => {
      setIsCreate(true);
    })
  }

  const getResultScript = () => {
    axios.get(
      `${API_URL}/api/scripts`,
      {
        headers: {
          Authorization: `Bearer ${userToken.accessToken}`,
        }
      }
    ).then((res) => {
      setResultScript(res.data.result.content[0])
    })
  }

  const goResult = async (data: WorldCupData) => {
    if (data.id) {
      setWorldCupResult(data);
      setIsDone21(true);
      setIsDone22(true);
      setIsNext7(false);
      await UseSleep(2000);
      setIsLoading(true);
    }
  }

  useEffect(() => {
    if (worldCupResult.id) {
      getResult();
    }
  }, [worldCupResult]);

  useEffect(() => {
    if (isCreate) {
      getResultScript();
    }
  }, [isCreate]);

  useEffect(() => {
    if (resultScript.id) {
      setIsLoading(false);
      setIsResult(true);
    }
  }, [resultScript]);

  return (
    <div className={styles.container}>
      {!isWorldCup8 &&
        <StartModal goStart={goStart}/>
      }

      {isNext1 &&
        <NextModal goNext={goNext2} left={worldCup8List[0]}
                   right={worldCup8List[1]}/>
      }

      {isNext2 &&
        <NextModal goNext={goNext3} left={worldCup8List[2]}
                   right={worldCup8List[3]}/>
      }

      {isNext3 &&
        <NextModal goNext={goNext4} left={worldCup8List[4]}
                   right={worldCup8List[5]}/>
      }

      {isNext4 &&
        <NextModal goNext={goNext41} left={worldCup8List[6]}
                   right={worldCup8List[7]}/>
      }

      {isNext5 &&
        <NextModal goNext={goNext42} left={worldCup4List[0]}
                   right={worldCup4List[1]}/>
      }

      {isNext6 &&
        <NextModal goNext={goNext21} left={worldCup4List[2]}
                   right={worldCup4List[3]}/>
      }

      {isNext7 &&
        <NextModal goNext={goResult} left={worldCup2List[0]}
                   right={worldCup2List[1]}/>
      }

      {isLoading &&
        <LoadingModal />
      }

      {isResult &&
        <ResultModal resultScript={resultScript} />
      }

      {worldCup1()}

      {worldCup2()}

      {worldCup4()}

      {worldCup8()}

    </div>
  );
}