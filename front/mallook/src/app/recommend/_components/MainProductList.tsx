import styles from "./mainProductList.module.css";
import Image from "next/image";

import {ProductListProps, Product, ScriptProducts} from "@/types";
import Link from "next/link";


export default function MainProductList(
  props:{
    scriptProduct: ScriptProducts,
  } ) {

  const line = (index: number) => {
    if (index < 4) {
      return (
        <div className={styles.line}></div>
      )
    }
  }

  const productList = () => {
    return (
      props.scriptProduct.products.length && props.scriptProduct.products.map((product, index) => {
        return (
          <Link style={{color: "black"}} href={`/product/${product.id}`} key={index}>
            <div className={styles.product}>
              <div className={styles.product__leftDiv}>
                <Image
                  className={styles.product__leftDiv__productImg}
                  src={product.image}
                  alt="상품 이미지"
                  width={200}
                  height={200}
                  unoptimized={true}
                />
              </div>

                <div className={styles.product__rightDiv}>
                    <span className={styles.product__rightDiv__productBrand}>{product.brandName}</span>
                    <span className={styles.product__rightDiv__productName}>{product.name}</span>
                    <span className={styles.product__rightDiv__productPrice}>{product.price.toLocaleString()} 원</span>
                </div>
            </div>
          </Link>
        );
      })
    );
  };


  return (
    <div className={styles.productList__container}>
      <div className={styles.productList}>
        {productList()}
      </div>
      <Link className={styles.link} href={`/recommend/${props.scriptProduct.id}`} >
        <span>더보기</span>
      </Link>
    </div>
  );
}