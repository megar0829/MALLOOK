package io.ssafy.mallook.domain.product.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import config.QueryDSLTestConfig;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.QProduct;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(QueryDSLTestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductCustomRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    private ProductCustomRepository productCustomRepository;

    @Autowired
    private ProductRepository productRepository;

    private EntityManager em;

    private List<Product> mockProducts = new ArrayList<>();


    @BeforeEach
    void init() {
        em = testEntityManager.getEntityManager();
        Product product = Product
                .builder()
                .name("테스트 바지")
                .mainCategory(MainCategory.TOP)
                .subCategory(SubCategory.FORMAL)
                .build();
        productRepository.save(product);
        mockProducts.add(product);
        em.flush();
        em.clear();
    }


    @Test
    void findAllProduct() {
        Page<ProductListDto> result = productCustomRepository.findAllProduct(Pageable.unpaged(), "mainCategory", "subCategory");

        List<ProductListDto> expectedList = mockProducts.stream()
                .map(ProductListDto::toDto)
                .toList();
        Page<ProductListDto> expectedPage = new PageImpl<>(expectedList);

        assertEquals(expectedPage, result);
    }
}