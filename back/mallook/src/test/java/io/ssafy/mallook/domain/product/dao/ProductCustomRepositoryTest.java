package io.ssafy.mallook.domain.product.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.global.config.QueryDSLConfig;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(QueryDSLConfig.class)
@ActiveProfiles(profiles = "dev")
@ComponentScan(basePackages = "io.ssafy.mallook.domain.product.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductCustomRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private ProductCustomRepository productCustomRepository;

    @Autowired
    private ProductRepository productRepository;

    private List<Product> mockProducts = new ArrayList<>();

    @BeforeEach
    void init() {
        entityManager.clear();
        queryFactory = new JPAQueryFactory(entityManager);
        Product product = Product
                .builder()
                .name("테스트 바지")
                .mainCategory(MainCategory.TOP)
                .subCategory(SubCategory.FORMAL)
                .build();
        productRepository.save(product);
        mockProducts.add(product);
        entityManager.flush();
        entityManager.clear();
    }

//    @Test
//    void findAllProduct() {
//
//        Slice<ProductListDto> result = productCustomRepository.findAllProduct(0, 2
//                MainCategory.TOP,
//                SubCategory.FORMAL);
//
//        List<ProductListDto> expectedList = mockProducts.stream()
//                .map(ProductListDto::toDto)
//                .collect(Collectors.toList());
//        Page<ProductListDto> expectedPage = new PageImpl<>(expectedList, pageable, expectedList.size());
//
//        assertEquals(result, expectedPage);
//    }
}
