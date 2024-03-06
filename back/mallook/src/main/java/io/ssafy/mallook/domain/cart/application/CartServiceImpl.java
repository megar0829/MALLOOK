package io.ssafy.mallook.domain.cart.application;

import io.ssafy.mallook.domain.cart.dao.CartRepository;
import io.ssafy.mallook.domain.cart.dto.request.CartInsertReq;
import io.ssafy.mallook.domain.cart.dto.response.CartDetailRes;
import io.ssafy.mallook.domain.cart.dto.response.CartPageRes;
import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.cart_product.dao.CartProductRepository;
import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.dao.ProductRepository;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.ExceptionHandler;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    private  final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;
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
        var cart = cartRepository.findMyCartByMember(memberId).orElse(new Cart());
        // 수량 및 색상 확인
        var sameProductCnt = cartProductRepository.CountSameProductInCart(cartInsertReq.productId());
        if (product.getQuantity()< sameProductCnt + cartInsertReq.productCount() || ! product.getColor().equals(cartInsertReq.productColor()) ) {
            throw new BaseExceptionHandler(ErrorCode.BAD_REQUEST_ERROR);
        }

        CartProduct cartProduct = new CartProduct().builder()
                .productCount(sameProductCnt + cartInsertReq.productCount())
                .productPrice(product.getPrice())
                .productName(product.getName())
                .productImage(product.getImage())
                .productSize(cartInsertReq.productSize())
                .productColor(cartInsertReq.productColor())
                .productFee(cartInsertReq.productFee())
                .build();
        cartProductRepository.save(cartProduct);

        List<CartProduct> cpList = cart.getCartProductList();
        cpList.add(cartProduct);
        cart.setCartProductList(cpList);
        cart.setMember(new Member(memberId));
        if (cart.getTotalFee() == null) {
            cart.setTotalFee(cartInsertReq.productFee() + cartInsertReq.productCount() *product.getPrice());
            cart.setTotalPrice(cartInsertReq.productCount() * product.getPrice());
        } else {
            cart.setTotalFee(cart.getTotalFee() + cartInsertReq.productCount() * product.getPrice());
            cart.setTotalPrice(cart.getTotalPrice() + cartInsertReq.productCount() * product.getPrice());
        }
        cartRepository.save(cart);
    }
    @Override
    @Transactional
    public void deleteProductInCart(Long cartProductId) {
        cartProductRepository.deleteCartProduct(cartProductId);
    }


}
