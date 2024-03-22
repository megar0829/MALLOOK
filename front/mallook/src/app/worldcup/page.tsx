"use client";

import Link from "next/link";
import React from "react";
import parse from "html-react-parser";

// export const metadata = {
//   title: "월드컵",
// };

const HtmlComponent = () => {
  // const htmlString = `
  //   <html>
  //     <head>
  //       <meta name="viewport" content="width=device-width" />
  //       <style type="text/css">
  //         #brandiProductDetailApi
  //         img {
  //           max-width:100%;
  //           height: auto;
  //         }
  //         #brandiProductDetailApi
  //         * {
  //           text-align:center;
  //           max-width:100%;
  //           font-size:inherit;
  //         }
  //         table {
  //             width:100%;
  //         }
  //         .brandiProductInfoHeader {
  //           font-size:11px;
  //           font-weight:700;
  //           padding-top:20px
  //         }
  //         .brandiProductInfoTitle {
  //           font-size:18px;
  //           font-weight:700;
  //           padding-top:20px
  //         }
  //         .brandiProductInfoTxt {
  //           float:right;
  //           font-weight:600;
  //           padding-bottom:5px;
  //         }
  //         .brandiProductSizeInfo {
  //           width:100%;
  //           border-top: 3px solid #000000;
  //           border-bottom:1px solid #bdbdbd;
  //           border-collapse:collapse;
  //           color:#525252;
  //           font-size: 12px;
  //           margin-bottom: 20px;
  //         }
  //         .brandiProductSizeInfo td {
  //           padding-top:20px;
  //           padding-bottom:20px;
  //           border-bottom: 1px solid #bdbdbd;
  //         }
  //         .brandiProductEtcInfo {
  //           width:100%;
  //           border-top: 3px solid #000000;
  //           border-bottom:3px solid #000000;
  //           border-collapse:collapse;
  //           color:#525252;
  //           font-size: 12px;
  //           margin-bottom: 20px;
  //         }
  //         .brandiProductEtcInfo td {
  //           padding:20px;
  //           border-bottom: 1px solid #bdbdbd;
  //         }
  //         .brandiSelectedTxt {
  //           font-weight: 800;
  //           font-size: 14px;
  //           color: #000000
  //         }
  //         .brandiSizeInfoImage {
  //           padding-top: 10px;
  //           padding-bottom: 30px;
  //           padding-left: 40px;
  //           padding-right: 40px;
  //         }
  //       </style>
  //     </head>
  //     <body>
  //       <div id="brandiProductDetailApi">
  //         <div>
  //           <img
  //               src="https://image.brandi.me/common/banner_view_imgwide.png"
  //           />
  //         </div>
  //         <div>
  //         <span
  //           style="font-size:11px"
  //         >
  //           &quot;MD COMMENT&quot;
  //           <br />
  //           <strong>23 F/W 하이버 가을 아우터 1위!
  //             <br />
  //             <span style="color:#d35400">누적 판매 5000장 돌파!</span>
  //           </strong>
  //         </span>
  //         <br />
  //         <br />
  //         <strong>
  //           <span style="font-size:11px">
  //             넉넉한 재고 보유로 오후 1시 이전 주문건
  //             <br />
  //             <span style="color:#d35400">무조건 당일발송!</span>
  //           </span>
  //         </strong>
  //         <br />
  //         <br />
  //         <br />
  //         <img src="http://ngjn123.negagea.kr/하이버/가을/아이캔밴딩청자켓/아이캔%20데님%20차이나%20자켓%20복사.jpg" />
  //       </div>
  //     </div>
  //   </body>
  // </html>
  // `;

  const htmlString = `
    <html><head><meta name="viewport" content="width=device-width" /></head><body><style type="text/css">#brandiProductDetailApi img{ max-width:100%; height: auto; }#brandiProductDetailApi *{text-align:center;max-width:100%;font-size:inherit;}table{width:100%;}.brandiProductInfoHeader{font-size:11px; font-weight:700; padding-top:20px}.brandiProductInfoTitle{font-size:18px; font-weight:700; padding-top:20px}.brandiProductInfoTxt{float:right; font-weight:600; padding-bottom:5px;}.brandiProductSizeInfo{width:100%; border-top: 3px solid #000000; border-bottom:1px solid #bdbdbd; border-collapse:collapse; color:#525252; font-size: 12px; margin-bottom: 20px;} .brandiProductSizeInfo td{padding-top:20px; padding-bottom:20px; border-bottom: 1px solid #bdbdbd;} .brandiProductEtcInfo{width:100%; border-top: 3px solid #000000; border-bottom:3px solid #000000; border-collapse:collapse; color:#525252; font-size: 12px; margin-bottom: 20px;} .brandiProductEtcInfo td{padding:20px; border-bottom: 1px solid #bdbdbd;} .brandiSelectedTxt{font-weight: 800; font-size: 14px; color: #000000} .brandiSizeInfoImage{padding-top: 10px; padding-bottom: 30px; padding-left: 40px; padding-right: 40px;}</style><div id="brandiProductDetailApi"><div><img src="https://image.brandi.me/common/banner_view_imgwide.png" /></div><div>
<p><br />
<span style="color:#ff0000"><strong><img alt="" src="https://image.brandi.me/cproduct/2022/09/28/20348_1664313820.png" /><br />
<br />
삐빅-!</strong></span><br />
해당 제품은&nbsp;<br />
&quot;당일출고&quot; 되는 제품입니다.<br />
<br />
<img alt="ezgif.com-gif-maker_(53).gif" src="https://shop-phinf.pstatic.net/20211019_254/163458430122239qnC_GIF/ezgif.com-gif-maker_(53).gif?type=w860" /><br />
<br />
<span style="background-color:#afeeee">지금이 오전 11시 전이라면?</span><br />
<span style="background-color:#afeeee">바로 주문주세요 !</span><br />
﻿</p>

<p id="SE-e108e135-e01c-4e9e-864c-1a0480742b48"><img alt="" src="https://image.brandi.me/cproduct/2022/09/28/20348_1664307904.png" /></p>
</div>

<div><br />
<img alt="" src="https://shop-phinf.pstatic.net/20220202_187/1643731191597OiMYj_GIF/ezgif.com-gif-maker_(1).gif?type=w860" style="width:600px" />
<p id="SE-ad9d0df0-b1a7-4ccd-ae1b-2c6dd8be8d9b"><img alt="" src="https://image.brandi.me/cproduct/2022/09/28/20348_1664307930.png" /><br />
&nbsp;</p>
</div>

<div>﻿<br />
<img alt="" src="https://shop-phinf.pstatic.net/20200229_290/1582964557832HqPBB_GIF/a.gif?type=w860" style="width:600px" /></div>

<div><img alt="" src="https://image.brandi.me/cproduct/2022/09/28/20348_1664307963.png" /><br />
<br />
<img alt="" src="https://image.brandi.me/cproduct/2022/09/28/20348_1664307986.png" /><br />
<br />
<img alt="" src="https://shop-phinf.pstatic.net/20210412_233/1618160506859HzEwd_GIF/ezgif.com-gif-maker_(28).gif?type=w860" /><br />
<br />
<span style="font-size:14px"><span style="color:#ff0000">그래서 부드럽습니다.</span></span><br />
<br />
<img alt="" src="https://shop-phinf.pstatic.net/20210412_254/16181600335445an3g_GIF/ezgif.com-gif-maker_(29).gif?type=w860" /><br />
<br />
<img alt="" src="https://image.brandi.me/cproduct/2022/09/28/20348_1664308006.png" /><br />
<br />
&nbsp;</div>

<div>﻿<img alt="" src="https://shop-phinf.pstatic.net/20210128_153/1611782046205HCTjH_GIF/ezgif.com-gif-maker_(11).gif?type=w860" style="width:252px" /><br />
﻿</div>

<div><img alt="" src="https://image.brandi.me/cproduct/2022/09/28/20348_1664308036.png" /><br />
&nbsp;</div>

<div><img alt="" src="https://image.brandi.me/cproduct/2022/09/28/20348_1664304408.png" /></div>

<div>﻿<br />
<img alt="" src="https://shop-phinf.pstatic.net/20200229_135/1582965196419QqP4V_GIF/c1-1.gif?type=w860" style="width:600px" />
<p id="SE-7909eeda-4fbc-458e-aebb-d833f7e78086"><img alt="" src="https://image.brandi.me/cproduct/2022/09/28/20348_1664311374.png" /><br />
﻿<br />
<img alt="분또8.png" src="https://shop-phinf.pstatic.net/20220928_24/1664311438582IPxSm_PNG/%EB%B6%84%EB%98%908.png?type=w860" style="width:860px" /><br />
<br />
<img alt="분또9.png" src="https://shop-phinf.pstatic.net/20220928_135/1664311449902WFg4G_PNG/%EB%B6%84%EB%98%909.png?type=w860" style="width:860px" /><br />
<br />
<img alt="분또10.png" src="https://shop-phinf.pstatic.net/20220928_114/1664311460797KJ17X_PNG/%EB%B6%84%EB%98%9010.png?type=w860" style="width:860px" /><br />
<br />
<img alt="분또11.png" src="https://shop-phinf.pstatic.net/20220928_97/1664311511399azmto_PNG/%EB%B6%84%EB%98%9011.png?type=w860" style="width:860px" /><br />
<br />
<img alt="분또12.png" src="https://shop-phinf.pstatic.net/20220928_115/1664311524632EBvHB_PNG/%EB%B6%84%EB%98%9012.png?type=w860" style="width:860px" /><br />
<br />
<img alt="분또13.png" src="https://shop-phinf.pstatic.net/20220928_242/1664311532230WzxUU_PNG/%EB%B6%84%EB%98%9013.png?type=w860" style="width:860px" /><br />
<br />
<img alt="분또14.png" src="https://shop-phinf.pstatic.net/20220928_10/1664311542177Er3mf_PNG/%EB%B6%84%EB%98%9014.png?type=w860" style="width:860px" /></p>

<p><br />
﻿</p>
</div>
</div></body></html>
  `;

  return (
    <div>
      {parse(htmlString)}
    </div>
  );
};

export default async function WorldCupPage() {
  return (
    <div>
      <h1>Welcome to Next.js!</h1>
      {HtmlComponent()}
    </div>
  );
}