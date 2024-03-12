package io.ssafy.mallook.domain.cart.application;

import io.ssafy.mallook.domain.cart.dao.CartRepository;
import io.ssafy.mallook.domain.cart.dto.request.CartDeleteReq;
import io.ssafy.mallook.domain.cart.dto.request.CartInsertReq;
import io.ssafy.mallook.domain.cart.dto.response.CartPageRes;
import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.cart_product.dao.CartProductRepository;
import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.dao.ProductRepository;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    private  final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    @Override
    @Transactional(readOnly = true)
    public CartPageRes findProductsInCart(Pageable pageable, UUID memberId) {
        var result = cartRepository.findProductsInCart(pageable, memberId);
        return new CartPageRes(result.getContent(), result.getTotalPages(), result.getNumber());
    }

    @Transactional
    @Override
    public void insertProductInCart(UUID memberId, CartInsertReq cartInsertReq) {
        var product = productRepository.findById(cartInsertReq.productId()).orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        var cart = cartRepository.findMyCartByMember(new Member(memberId));
        var sameProductCnt = cart.map(value -> cartProductRepository.CountSameProductInCart(value.getId(), cartInsertReq.productId()) + cartInsertReq.productCount())
                .orElseGet(cartInsertReq::productCount);
        // 수량, 색상 확인
        if (product.getQuantity()< sameProductCnt|| ! product.getColor().equals(cartInsertReq.productColor()) ) {
            throw new BaseExceptionHandler(ErrorCode.BAD_REQUEST_ERROR);
        }
        // 카트 내 총계 계산(totalFee , totalPrice)
        var totalPrice = cart.map(value -> value.getTotalPrice() + cartInsertReq.productCount() * product.getPrice()).orElseGet(() -> cartInsertReq.productCount() * product.getPrice());
        var totalFee = cart.map(value -> value.getTotalFee() + cartInsertReq.productFee()).orElseGet(cartInsertReq::productFee);
        var totalCnt = cart.map(value -> value.getTotalCount() + cartInsertReq.productCount()).orElseGet(cartInsertReq::productCount);
        Cart rs ;
        // 카트 생성
        if (cart.isEmpty()){
            Cart cc = Cart.builder()
                    .totalFee(totalFee)
                    .totalCount(totalCnt)
                    .totalPrice(totalPrice)
                    .member(new Member(memberId))
                    .build();
            rs = cartRepository.save(cc);
        // 카트 수정
        } else {
            cart.get().setTotalPrice(totalPrice);
            cart.get().setTotalFee(totalFee);
            cart.get().setTotalCount(totalCnt);
            rs = cartRepository.save(cart.get());
        }
        CartProduct cartProduct = CartProduct.builder()
                .cart(rs)
                .product(product)
                .productCount(cartInsertReq.productCount())
                .productPrice(product.getPrice())
                .productName(product.getName())
                .productImage(product.getImage())
                .productSize(cartInsertReq.productSize())
                .productColor(cartInsertReq.productColor())
                .productFee(cartInsertReq.productFee())
                .build();
        cartProductRepository.save(cartProduct);
    }
    @Override
    @Transactional
    public void deleteProductInCart(CartDeleteReq cartDeleteReq) {
        cartProductRepository.deleteCartProduct(cartDeleteReq.cartProductList());
    }
}
