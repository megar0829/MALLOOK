
import Link from "next/link";
import parse from "html-react-parser";
export const metadata = {
  title: "월드컵",
};

const HtmlComponent = () => {
  const htmlString = `
    <html>
      <head>
        <meta name="viewport" content="width=device-width" />
        <style type="text/css">
          #brandiProductDetailApi 
          img { 
            max-width:100%; 
            height: auto; 
          }
          #brandiProductDetailApi 
          * {
            text-align:center;
            max-width:100%;
            font-size:inherit;
          }
          table {
              width:100%;
          }
          .brandiProductInfoHeader {
            font-size:11px; 
            font-weight:700; 
            padding-top:20px
          }
          .brandiProductInfoTitle {
            font-size:18px; 
            font-weight:700; 
            padding-top:20px
          }
          .brandiProductInfoTxt {
            float:right; 
            font-weight:600; 
            padding-bottom:5px;
          }
          .brandiProductSizeInfo {
            width:100%; 
            border-top: 3px solid #000000; 
            border-bottom:1px solid #bdbdbd; 
            border-collapse:collapse; 
            color:#525252; 
            font-size: 12px; 
            margin-bottom: 20px;
          } 
          .brandiProductSizeInfo td {
            padding-top:20px; 
            padding-bottom:20px; 
            border-bottom: 1px solid #bdbdbd;
          } 
          .brandiProductEtcInfo {
            width:100%; 
            border-top: 3px solid #000000; 
            border-bottom:3px solid #000000; 
            border-collapse:collapse; 
            color:#525252; 
            font-size: 12px; 
            margin-bottom: 20px;
          } 
          .brandiProductEtcInfo td {
            padding:20px; 
            border-bottom: 1px solid #bdbdbd;
          } 
          .brandiSelectedTxt {
            font-weight: 800; 
            font-size: 14px; 
            color: #000000
          } 
          .brandiSizeInfoImage {
            padding-top: 10px; 
            padding-bottom: 30px; 
            padding-left: 40px; 
            padding-right: 40px;
          }
        </style>
      </head>
      <body>
        <div id="brandiProductDetailApi">
          <div>
            <img 
                src="https://image.brandi.me/common/banner_view_imgwide.png" 
            />
          </div>
          <div>
          <span 
            style="font-size:11px"
          >
            &quot;MD COMMENT&quot;
            <br />
            <strong>23 F/W 하이버 가을 아우터 1위!
              <br />
              <span style="color:#d35400">누적 판매 5000장 돌파!</span>
            </strong>
          </span>
          <br />
          <br />
          <strong>
            <span style="font-size:11px">
              넉넉한 재고 보유로 오후 1시 이전 주문건
              <br />
              <span style="color:#d35400">무조건 당일발송!</span>
            </span>
          </strong>
          <br />
          <br />
          <br />
          <img src="http://ngjn123.negagea.kr/하이버/가을/아이캔밴딩청자켓/아이캔%20데님%20차이나%20자켓%20복사.jpg" />
        </div>
      </div>
    </body>
  </html>
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
