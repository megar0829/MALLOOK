package io.ssafy.mallook.domain.cart.dao;

import io.ssafy.mallook.domain.cart.dto.response.CartDetailRes;
import io.ssafy.mallook.domain.cart.entity.Cart;

import io.ssafy.mallook.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(
            """
            select c from Cart c
            where c.status = true and c.member.id = :memberId
            """
    )
    Optional<Cart> findMyCartByMember(@Param("memberId") UUID memberId);

    @Query(
            """
            select new io.ssafy.mallook.domain.cart.dto.response.CartDetailRes(
                c.id, cp.id, cp.productPrice, cp.productCount, cp.productName, cp.productImage, cp.productSize, cp.productColor, cp.productFee
            ) 
            from Cart c 
            join CartProduct cp 
            where c.id = cp.cart.id and c.member.id = :memberId and c.status = true
            """
    )
    Page<CartDetailRes> findProductsInCart(Pageable pageable, @Param("memberId") UUID memberId);


}
