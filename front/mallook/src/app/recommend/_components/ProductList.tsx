import styles from "./productList.module.css";
import {object} from "prop-types";
import Image from "next/image";

import {ProductListProps, Product} from "@/constants";

export default function ProductList( { productLeft, productRight }: ProductListProps) {

  const productListLeft = () => {
    return (
      productLeft.map((product, index) => {
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

            {index != productLeft.length - 1 &&
              <div className={styles.line}></div>
            }
          </div>
        );
      })
    );
  };

  const productListRight = () => {
    return (
      productRight.map((product, index) => {
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

            {index != productLeft.length - 1 &&
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
    </div>
  );
}