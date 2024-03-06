package io.ssafy.mallook.domain.cart_product.dao;

import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    @Modifying
    @Query("""
        update CartProduct cp set cp.status = false
        where cp.id = :id and cp.status = true
    """)
    void deleteCartProduct(@Param("id") Long id);
}
