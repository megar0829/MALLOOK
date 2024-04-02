import styles from "./mainProductList.module.css";
import {object} from "prop-types";
import Image, {StaticImageData} from "next/image";

import {ProductListProps, Product} from "@/types";
import Link from "next/link";

interface SampleProduct {
  productImg: string | StaticImageData;
  name: string;
  brand: string;
  price: number;
}

export default function MainProductList(props:{ productLeft: SampleProduct[], productRight: SampleProduct[], codyId: number } ) {

  const productListLeft = () => {
    return (
      props.productLeft.map((product, index) => {
        return (
          <div className={styles.product} key={index}>
            <div className={styles.product__leftDiv}>
              <Image className={styles.product__leftDiv__productImg} src={product.productImg} alt="상품 이미지" />
            </div>

            <div className={styles.product__rightDiv}>
              <span className={styles.product__rightDiv__productName}>{product.name}</span>
              <span className={styles.product__rightDiv__productBrand}>{product.brand}</span>
              <span className={styles.product__rightDiv__productPrice}>{product.price.toLocaleString()} 원</span>
            </div>

            {index != props.productLeft.length - 1 &&
              <div className={styles.line}></div>
            }
          </div>
        );
      })
    );
  };

  const productListRight = () => {
    return (
      props.productRight.map((product, index) => {
        return (
          <div className={styles.product} key={index}>
            <div className={styles.product__leftDiv}>
              <Image className={styles.product__leftDiv__productImg} src={product.productImg} alt="상품 이미지" />
            </div>

            <div className={styles.product__rightDiv}>
              <span className={styles.product__rightDiv__productName}>{product.name}</span>
              <span className={styles.product__rightDiv__productBrand}>{product.brand}</span>
              <span className={styles.product__rightDiv__productPrice}>{product.price.toLocaleString()} 원</span>
            </div>

            {index != props.productLeft.length - 1 &&
                <div className={styles.line}></div>
            }
          </div>
        );
      })
    );
  };

  return (
    <div className={styles.productList__container}>
      <div className={styles.productList}>
        {productListLeft()}
      </div>
      <div className={styles.productList}>
        {productListRight()}
      </div>
      <Link className={styles.link} href={`/recommend/${props.codyId}`} >
        <span>더보기</span>
      </Link>
    </div>
  );
}