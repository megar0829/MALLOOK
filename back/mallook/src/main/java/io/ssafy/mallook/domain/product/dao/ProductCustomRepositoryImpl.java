package io.ssafy.mallook.domain.product.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.QProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ssafy.mallook.domain.product.entity.QProduct.product;
@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findAllProduct(Pageable pageable,
                                        String mainCategory,
                                        String subCategory) {
        JPAQuery<Product> query = jpaQueryFactory
                .selectFrom(product)
                .where(allEq(mainCategory, subCategory));
        List<Product> products = this.
    }

    private BooleanExpression allEq(String mainCategory, String subCategory) {
        return mainCategoryEq(mainCategory).and(subCategoryEq(subCategory));
    }

    private BooleanExpression mainCategoryEq(String mainCategoryEq) {
        return mainCategoryEq != null ? QProduct.product.mainCategory.eq(MainCategory.valueOf(mainCategoryEq)) : null;
    }

    private BooleanExpression subCategoryEq(String subCategoryEq) {
        return subCategoryEq != null ? QProduct.product.mainCategory.eq(MainCategory.valueOf(subCategoryEq)) : null;
    }
}
