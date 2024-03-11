package io.ssafy.mallook.domain.shoppingmall.application;

import io.ssafy.mallook.domain.shoppingmall.dao.ShoppingMallRepository;
import io.ssafy.mallook.domain.shoppingmall.entity.ShoppingMall;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShoppingMallServiceImplTest {

    @Mock
    private ShoppingMallRepository shoppingMallRepository;

    @Mock
    private EntityManager entityManager;

    private ShoppingMall shoppingMall;

    @BeforeEach
    void setUp() {
        shoppingMall = ShoppingMall
                .builder()
                .name("테스트 쇼핑몰")
                .url("www.test-url.com")
                .build();
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void getAllMallList() {
        // given
        Pageable pageable = PageRequest.of(0, 20);

        // when
        Page<ShoppingMall> emptyPage = new PageImpl<>(Collections.emptyList());
        Mockito.when(shoppingMallRepository.findAll(pageable)).thenReturn(emptyPage);
        Page<ShoppingMall> result = shoppingMallRepository.findAll(pageable);

        // then
        Mockito.verify(shoppingMallRepository, Mockito.times(1)).findAll(pageable);
    }
}