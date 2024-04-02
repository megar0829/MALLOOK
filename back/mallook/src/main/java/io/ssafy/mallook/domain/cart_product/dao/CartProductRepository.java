package io.ssafy.mallook.domain.cart_product.dao;

import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    @Query("select max(cp.id) from CartProduct cp where cp.cart = :cart")
    Long getMaxId(@Param("cart") Cart cart);

    @Query(
            """
                    select COALESCE(sum(cp.productCount), 0)
                    from CartProduct cp
                    join cp.cart c
                    where c.id = :cartId and cp.product = :productId
                    """
    )
    Long CountSameProductInCart(@Param("cartId") Long cartId, @Param("productId") String productId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
                update CartProduct cp set cp.status = false
                where cp.id = :cartProductId and cp.status = true
            """)
    void deleteCartProduct(@Param("cartProductId") Long cartProductId);


    @Query(
            """
            update CartProduct c set c.status = false
            where c.cart.id = :cart
        """
    )
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    void deleteByCart(@Param("cart") Long cartId);
}
