package io.ssafy.mallook.domain.product.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.QProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements  ProductCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findAllProduct() {
        QProduct qProduct = QProduct.product;
        return jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.brandName.eq("바지")).fetch();
    }
}
