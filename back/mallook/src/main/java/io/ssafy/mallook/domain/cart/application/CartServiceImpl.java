package io.ssafy.mallook.domain.cart.application;

import io.ssafy.mallook.domain.cart.dao.CartRepository;
import io.ssafy.mallook.domain.cart.dto.request.CartDeleteReq;
import io.ssafy.mallook.domain.cart.dto.request.CartInsertReq;
import io.ssafy.mallook.domain.cart.dto.response.CartDetailRes;
import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.cart_product.dao.CartProductRepository;
import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.dao.jpa.ProductRepository;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    private  final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;

    @Override
    public Slice<CartDetailRes> findProductsInCartFirst(Pageable pageable, UUID memberId) {
        Cart cart = cartRepository.findMyCartByMember(new Member(memberId))
                .orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        Long maxId = cartProductRepository.getMaxId(cart);
        return cartRepository.findProductsInCart(pageable, memberId, maxId+1);
    }

    @Override
    public Slice<CartDetailRes> findProductsInCart(Pageable pageable, UUID memberId, Long cursor) {
        return cartRepository.findProductsInCart(pageable, memberId, cursor+1);
    }

    @Override
    @Transactional
    public void insertProductInCart(UUID memberId, CartInsertReq cartInsertReq) {
        Product product = productRepository.findById(cartInsertReq.productId()).orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        Cart cart = cartRepository.findMyCartByMember(new Member(memberId)).orElse(new Cart());
        var sameProductCnt = cart.getId() == null? cartInsertReq.count(): cartProductRepository.CountSameProductInCart(cart.getId(), cartInsertReq.productId())+ cartInsertReq.count();
        // 수량, 색상 확인
        if (product.getQuantity()< sameProductCnt|| ! product.getColor().equals(cartInsertReq.color()) ) {
            throw new BaseExceptionHandler(ErrorCode.BAD_REQUEST_ERROR);
        }
        // 카트 내 총계 계산 (totalFee , totalPrice)
        var totalPrice = cart.getTotalPrice() == null ? cartInsertReq.count() * product.getPrice(): cartInsertReq.count() * product.getPrice()+ cart.getTotalPrice() ;
        var totalFee = cart.getTotalFee() == null ? cartInsertReq.fee() : cart.getTotalFee() + cartInsertReq.fee();
        var totalCnt = cart.getTotalCount() == null ? cartInsertReq.count() : cart.getTotalCount() + cartInsertReq.count(); ;
        
        // 카트 저장 및 수정
        cart.setTotalPrice(totalPrice);
        cart.setTotalFee(totalFee);
        cart.setTotalCount(totalCnt);
        cart.setMember(new Member(memberId));
        Cart rs = cartRepository.save(cart);

        // cartproduct 저장
        CartProduct cartProduct = CartProduct.builder()
                .cart(rs)
                .product(product)
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
    public void deleteProductInCart(UUID memberId, CartDeleteReq cartDeleteReq) {
        Cart cart = cartRepository.findMyCartByMember(new Member(memberId))
                .orElseThrow(()->new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        CartProduct cartProduct = cartProductRepository.findById(cartDeleteReq.cartProductId())
                .orElseThrow(()->new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        // 총계 계산
        cart.setTotalCount(cart.getTotalCount() - cartProduct.getProductCount());
        cart.setTotalFee(cart.getTotalFee() - cartProduct.getProductFee());
        cart.setTotalPrice(cart.getTotalPrice() - cartProduct.getProductPrice());
        cartRepository.save(cart);
        cartProductRepository.deleteCartProduct(cartDeleteReq.cartProductId());
    }
}
