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
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByMember_Id(UUID memberId);

    @Query(
            """
            select new io.ssafy.mallook.domain.cart.dto.response.CartDetailRes(
                c.id, cp.id, cp.productPrice, cp.productCount, cp.productName, cp.productImage, cp.productSize, cp.productColor, cp.productFee
            ) 
            from Cart c 
            join CartProduct cp 
            where c.id = cp.cart.id and c.member.id = :memberId and cp.product.id = :productId and c.status = true
            """
    )
    CartDetailRes findProductDetailInCart(@Param("memberId") UUID memberId, @Param("productId") Long productId);

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
