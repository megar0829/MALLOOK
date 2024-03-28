package io.ssafy.mallook.domain.product.dao.jpa;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
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

        return new SliceImpl<>(products.stream()
                .limit(pageable.getPageSize())
                .map(ProductListDto::toDto)
                .collect(Collectors.toList()),
                pageable,
                products.size() > pageable.getPageSize());
    }

    private BooleanExpression allEq(MainCategory mainCategory,
                                    SubCategory subCategory) {
        return mainCategoryEq(mainCategory).and(subCategoryEq(subCategory));
    }

    private BooleanExpression mainCategoryEq(MainCategory mainCategoryEq) {
        return !Objects.isNull(mainCategoryEq) ? product.mainCategory.eq(mainCategoryEq) : Expressions.asBoolean(true).isTrue();
    }

    private BooleanExpression subCategoryEq(SubCategory subCategoryEq) {
        return !Objects.isNull(subCategoryEq) ? product.subCategory.eq(subCategoryEq) : Expressions.asBoolean(true).isTrue();
    }
}
