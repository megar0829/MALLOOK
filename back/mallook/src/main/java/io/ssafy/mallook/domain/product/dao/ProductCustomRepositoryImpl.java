package io.ssafy.mallook.domain.product.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.QProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findAllProduct(String genderCategory,
                                        String mainCategory,
                                        String subCategory) {
        return jpaQueryFactory.
                selectFrom(QProduct.product)
                .where((allEq(mainCategory, subCategory)))
                .fetch();
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
