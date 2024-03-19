package io.ssafy.mallook.domain.cart_product.dao;

import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    @Query(
            """
            select COALESCE(sum(cp.productCount), 0)
            from CartProduct cp
            join cp.cart c
            where c.id = :cartId and cp.product.id = :productId
            """
    )
    Long CountSameProductInCart(@Param("cartId") Long cartId, @Param("productId") Long productId);
    @Modifying(clearAutomatically = true)
    @Query("""
        update CartProduct cp set cp.status = false
        where cp.id = :cartProductId and cp.status = true
    """)
    void deleteCartProduct(@Param("cartProductId") Long cartProductId);
}
