package io.ssafy.mallook.domain.cart.dao;

import io.ssafy.mallook.domain.cart.dto.response.CartDetailRes;
import io.ssafy.mallook.domain.cart.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(
            """
            select new io.ssafy.mallook.domain.cart.dto.response.CartDetailRes(
                c.id, cp.id, cp.product.id, cp.productPrice, cp.productCount, cp.productName, cp.productImage, cp.productSize, cp.productColor, cp.productFee
            )
            from Cart c
            join CartProduct cp on cp.cart.id = c.id
            where c.member.id = :memberId and c.status = true and cp.status = true
            order by cp.id
            """
    )
    Page<CartDetailRes> findProductsInCart(Pageable pageable, @Param("memberId") UUID memberId);
    @Query(
            """
            select c from Cart c
            where c.status = true and c.member.id = :memberId
            """
    )
    Optional<Cart> findMyCartByMember(@Param("memberId") UUID memberId);



}
