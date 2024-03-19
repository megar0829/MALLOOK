package io.ssafy.mallook.domain.product.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.domain.shoppingmall.dao.ShoppingMallRepository;
import io.ssafy.mallook.domain.shoppingmall.entity.ShoppingMall;
import io.ssafy.mallook.global.config.QueryDSLConfig;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QueryDSLConfig.class)
@ActiveProfiles(profiles = "test")
@ComponentScan(basePackages = "io.ssafy.mallook.domain.product.dao")
class ProductCustomRepositoryTest {

    @Autowired
    private ShoppingMallRepository shoppingMallRepository;

    private ShoppingMall shoppingMall;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private ProductCustomRepository productCustomRepository;

    private Product product;

    @Autowired
    private ProductRepository productRepository;

    private List<Product> mockProducts = new ArrayList<>();

    @BeforeEach
    void init() {
        queryFactory = new JPAQueryFactory(entityManager);
        shoppingMall = getTestShoppingmall();
        shoppingMallRepository.save(shoppingMall);
        entityManager.flush();
        product = getTestProduct();
        productRepository.save(product);
        mockProducts.add(product);
        entityManager.flush();
        entityManager.clear();
    }

    private Product getTestProduct() {
        return Product.builder()
                .shopingmall(shoppingMall)
                .mainCategory(MainCategory.TOP)
                .subCategory(SubCategory.SPORT)
                .name("테스트 상의")
                .price(1000)
                .quantity(10)
                .size("Large")
                .color("white")
                .fee(500)
                .build();
    }

    private ShoppingMall getTestShoppingmall() {
        return ShoppingMall.builder()
                .name("테스트 쇼핑몰")
                .url("www.test.com")
                .build();
    }


    @Test
    void findAllProduct() {
        Pageable pageable = PageRequest.of(0, 20);
        Slice<ProductListDto> result = productCustomRepository.findAllProduct(
                product.getId() + 1,
                pageable,
                MainCategory.TOP,
                SubCategory.SPORT);

        List<ProductListDto> expectedList = mockProducts.stream()
                .map(ProductListDto::toDto)
                .collect(toList());

        assertThat(result.getContent()).usingRecursiveComparison().isEqualTo(expectedList);
        assertThat(result.getNumber()).isEqualTo(pageable.getPageNumber());
        assertThat(result.getSize()).isEqualTo(pageable.getPageSize());
        assertThat(result.hasNext()).isFalse();
    }
}
