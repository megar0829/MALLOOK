package io.ssafy.mallook.domain.cart.application;

import io.ssafy.mallook.domain.cart.dao.CartRepository;
import io.ssafy.mallook.domain.cart.dto.request.CartInsertReq;
import io.ssafy.mallook.domain.cart.dto.response.CartDetailRes;
import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.cart_product.dao.CartProductRepository;
import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import io.ssafy.mallook.domain.product.dao.ProductRepository;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.ExceptionHandler;
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
    @Transactional
    @Override
    public void insertProductInCart(UUID memberId, CartInsertReq cartInsertReq) {
        var product = productRepository.findById(cartInsertReq.productId()).orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        var cartProduct = cartRepository.findProductDetailInCart(memberId, cartInsertReq.productId());
        Long cnt = 0L;
        if (Objects.nonNull(cartProduct)) {
            cnt = cartProduct.productCount();
        }
        // 수량 및 색상 확인
        if (product.getQuantity()< cnt + cartInsertReq.productCount() || ! product.getColor().equals(cartInsertReq.productColor()) ) {
            throw new BaseExceptionHandler(ErrorCode.BAD_REQUEST_ERROR);
        }
        CartProduct cp = new CartProduct().builder()
                .productCount(cnt + cartInsertReq.productCount())
                .productPrice(product.getPrice())
                .productName(product.getName())
                .productImage(product.getImage())
                .productSize(cartInsertReq.productSize())
                .productColor(cartInsertReq.productColor())
                .productFee(cartInsertReq.productFee())
                .build();
        cartProductRepository.save(cp);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartDetailRes> findProductsInCart(UUID memberId) {
        return cartRepository.findProductsInCart(memberId);
    }

    @Override
    @Transactional
    public void deleteProductInCart(Long cartProductId) {
        cartProductRepository.deleteCartProduct(cartProductId);
    }


}
