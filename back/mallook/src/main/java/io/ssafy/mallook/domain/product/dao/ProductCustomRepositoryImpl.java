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
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static io.ssafy.mallook.domain.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<ProductListDto> findAllProduct(Long cursor, Pageable pageable, MainCategory mainCategory, SubCategory subCategory) {
        List<Product> products = jpaQueryFactory
                .selectFrom(product)
                .where(allEq(mainCategory, subCategory),
                        product.id.lt(cursor))
                .orderBy(product.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = products.size() > pageable.getPageSize();
        List<ProductListDto> productDtos = products.stream()
                .limit(pageable.getPageSize())
                .map(ProductListDto::toDto)
                .collect(Collectors.toList());

        return new SliceImpl<>(productDtos, pageable, hasNext);
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
