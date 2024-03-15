package io.ssafy.mallook.domain.product.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.QProduct;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ssafy.mallook.domain.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ProductListDto> findAllProduct(Pageable pageable,
                                        MainCategory mainCategory,
                                        SubCategory subCategory) {
        List<Product> products = jpaQueryFactory
                .selectFrom(product)
                .where(allEq(mainCategory, subCategory))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(
                products.stream()
                        .map(ProductListDto::toDto)
                        .toList(),
                pageable,
                products.size());
    }

    private BooleanExpression allEq(MainCategory mainCategory,
                                    SubCategory subCategory) {
        return mainCategoryEq(mainCategory).and(subCategoryEq(subCategory));
    }

    private BooleanExpression mainCategoryEq(MainCategory mainCategoryEq) {
        return mainCategoryEq != null ? product.mainCategory.eq(mainCategoryEq) : Expressions.asBoolean(true).isTrue();
    }

    private BooleanExpression subCategoryEq(SubCategory subCategoryEq) {
        return subCategoryEq != null ? product.subCategory.eq(subCategoryEq) : Expressions.asBoolean(true).isTrue();
    }
}
