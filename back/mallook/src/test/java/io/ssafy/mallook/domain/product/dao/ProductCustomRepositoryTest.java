package io.ssafy.mallook.domain.product.dao;

import config.QueryDSLTestConfig;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(QueryDSLTestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductCustomRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductCustomRepository productCustomRepository;

    @Autowired
    private ProductRepository productRepository;

    private List<Product> mockProducts = new ArrayList<>();


    @BeforeEach
    void init() {
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


    @Test
    void findAllProduct() {
        Pageable pageable = PageRequest.of(0, 2);

        Page<ProductListDto> result = productCustomRepository.findAllProduct(pageable,
                "TOP",
                "FORMAL");

        List<ProductListDto> expectedList = mockProducts.stream()
                .map(ProductListDto::toDto)
                .toList();
        Page<ProductListDto> expectedPage = new PageImpl<>(expectedList);

        assertEquals(expectedPage, result);
    }
}