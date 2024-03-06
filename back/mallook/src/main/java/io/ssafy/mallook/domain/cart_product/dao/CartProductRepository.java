package io.ssafy.mallook.domain.cart_product.dao;

import io.ssafy.mallook.domain.cart.dto.response.CartDetailRes;
import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    @Modifying
    @Query("""
        update CartProduct cp set cp.status = false
        where cp.id = :id and cp.status = true
    """)
    void deleteCartProduct(@Param("id") Long id);
    @Query(
            """
            select sum(cp.productCount)
            from CartProduct cp
            join cp.cart c
            where c.member.id = :memberId and c.status = true
            and cp.product.id = :productId and cp.status = true
            """
    )
    Long CountSameProductInCart(@Param("memberId") UUID memberId, @Param("productId") Long productId);
}
