package io.ssafy.mallook.domain.product.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.mallook.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import static io.ssafy.mallook.domain.product.Qproduct.product;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements  ProductCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findAllProduct() {
        return jpaQueryFactory.select(Product);
    }
}
