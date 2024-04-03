package io.ssafy.mallook.domain.cart.application;

import io.ssafy.mallook.domain.cart.dao.CartRepository;
import io.ssafy.mallook.domain.cart.dto.request.CartInsertReq;
import io.ssafy.mallook.domain.cart.dto.request.CartProductDeleteReq;
import io.ssafy.mallook.domain.cart.dto.response.CartDetailRes;
import io.ssafy.mallook.domain.cart.dto.response.CartPageRes;
import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.cart_product.dao.CartProductRepository;
import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.dao.mongo.ProductsRepository;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductsRepository productsRepository;

    @Override
    public List<CartDetailRes> findProductsInCart(UUID memberId) {
        return cartRepository.findProductsInCart(memberId);


    }

    @Override
    @Transactional
    public void insertProductInCart(UUID memberId, CartInsertReq cartInsertReq) {
        Products product = productsRepository.findById(cartInsertReq.productId())
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        Cart memberCart = cartRepository.findMyCartByMember(new Member(memberId)).orElse(new Cart());

        // 카트 내 총계 계산 (totalFee , totalPrice)
        var totalPrice = memberCart.getTotalPrice() == null ? cartInsertReq.count() * product.getPrice() : cartInsertReq.count() * product.getPrice() + memberCart.getTotalPrice();
        var totalFee = memberCart.getTotalFee() == null ? cartInsertReq.fee() : memberCart.getTotalFee() + cartInsertReq.fee();
        var totalCnt = memberCart.getTotalCount() == null ? cartInsertReq.count() : memberCart.getTotalCount() + cartInsertReq.count();
        ;

        // 카트 저장 및 수정
        memberCart.setTotalPrice((long) totalPrice);
        memberCart.setTotalFee(totalFee);
        memberCart.setTotalCount((long) totalCnt);
        memberCart.setMember(Member.builder().id(memberId).build());
        Cart cart = cartRepository.save(memberCart);

        // cartproduct 저장
        CartProduct cartProduct = CartProduct.builder()
                .cart(cart)
                .product(cartInsertReq.productId())
                .productCount(cartInsertReq.count())
                .productPrice(cartInsertReq.price())
                .productName(product.getName())
                .productImage(product.getImage())
                .productSize(cartInsertReq.size())
                .productColor(cartInsertReq.color())
                .productFee(cartInsertReq.fee())
                .build();
        cartProductRepository.save(cartProduct);
    }

    @Override
    @Transactional
    public void deleteProductInCart(UUID memberId, CartProductDeleteReq cartDeleteReq) {
        Cart cart = cartRepository.findMyCartByMember(new Member(memberId))
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        CartProduct cartProduct = cartProductRepository.findById(cartDeleteReq.cartProductId())
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        // 총계 계산
        cart.setTotalCount(cart.getTotalCount() - cartProduct.getProductCount());
        cart.setTotalFee(cart.getTotalFee() - cartProduct.getProductFee());
        cart.setTotalPrice(cart.getTotalPrice() - cartProduct.getProductPrice());
        cartRepository.save(cart);
        cartProductRepository.deleteCartProduct(cartDeleteReq.cartProductId());
    }

    @Override
    @Transactional
    public void deleteCart(UUID memberId) {
        var cart = cartRepository.findMyCartByMember(Member.builder().id(memberId).build())
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        cartRepository.deleteMyCart(memberId);
        cartProductRepository.deleteByCart(cart.getId());
    }
}
