package io.ssafy.mallook.domain.orders.application;

import io.ssafy.mallook.domain.cart.dao.CartRepository;
import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.cart_product.dao.CartProductRepository;
import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.orders.dao.OrderRepository;
import io.ssafy.mallook.domain.orders.dto.request.OrderCreateDto;
import io.ssafy.mallook.domain.orders.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.orders.dto.request.OrderDirectInsertReq;
import io.ssafy.mallook.domain.orders.dto.request.OrderInsertReq;
import io.ssafy.mallook.domain.orders.dto.response.OrderDetailDto;
import io.ssafy.mallook.domain.orders.dto.response.OrderListDto;
import io.ssafy.mallook.domain.orders.entity.Orders;
import io.ssafy.mallook.domain.product.dao.mongo.ProductsRepository;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product_history.dao.ProductHistoryRepository;
import io.ssafy.mallook.domain.product_history.entity.ProductHistory;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductHistoryRepository productHistoryRepository;
    private final ProductsRepository productsRepository;
    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;
    @Override
    public Slice<OrderListDto> getOrderList(Long cursor, UUID id, Pageable pageable) {
        Member proxyMember = memberRepository.getReferenceById(id);

        return orderRepository.findByIdLessThanAndMemberOrderByIdDesc(cursor, proxyMember, pageable)
                .map(OrderListDto::toDto);
    }

    @Override
    public OrderDetailDto getOrderDetail(Long id) {
        return orderRepository.findById(id)
                .map(OrderDetailDto::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void createOrder(UUID id, OrderCreateDto createDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        orderRepository.save(createDto.toEntity(proxyMember));
    }

    @Override
    @Transactional
    public void insertDirectOrder(UUID id, OrderDirectInsertReq orderDirectInsertReq) {
        Member proxyMember = memberRepository.getReferenceById(id);
        // order 저장
        var order = orderRepository.save(Orders.builder()
                .totalFee(orderDirectInsertReq.totalFee())
                .totalCount(orderDirectInsertReq.totalCount())
                .totalPrice(orderDirectInsertReq.totalPrice())
                .member(proxyMember)
                .build());
        // producthistory 저장, 장바구니 삭제
        var productInfo = orderDirectInsertReq.products();
        Products product = productsRepository.findById(productInfo.productId())
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        productHistoryRepository.save(ProductHistory.builder()
                .productCount(productInfo.count())
                .productPrice(product.getPrice())
                .productName(product.getName())
                .productImage(product.getImage())
                .productSize(productInfo.size())
                .productColor(productInfo.color())
                .orders(order)
                .build());

    }

    @Override
    @Transactional
    public void insertOrder(UUID id, OrderInsertReq orderInsertReq) {
        Member proxyMember = memberRepository.getReferenceById(id);
        // order 저장
        var order = orderRepository.save(Orders.builder()
                .totalFee(orderInsertReq.totalFee())
                .totalCount(orderInsertReq.totalCount())
                .totalPrice(orderInsertReq.totalPrice())
                .member(proxyMember)
                .build());
        // producthistory 저장
        orderInsertReq.cartProductList().forEach((cartProductId) -> {
            System.out.println("tt" + cartProductId);
            CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                    .orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
            productHistoryRepository.save(ProductHistory.builder()
                    .productCount(cartProduct.getProductCount())
                    .productPrice(cartProduct.getProductPrice())
                    .productName(cartProduct.getProductName())
                    .productImage(cartProduct.getProductImage())
                    .productSize(cartProduct.getProductSize())
                    .productColor(cartProduct.getProductColor())
                    .orders(order)
                    .build());
            // cartProduct 삭제
            cartProductRepository.deleteCartProduct(cartProductId);
        });
        System.out.println("$$$$$$$$$$$4");
        Cart cart = cartRepository.findMyCartByMember(proxyMember)
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        System.out.println("dfdfdf");
        // cart 업데이트..
        cart.setTotalCount(cart.getTotalCount() - orderInsertReq.totalCount());
        cart.setTotalFee(cart.getTotalFee() - orderInsertReq.totalFee());
        cart.setTotalPrice(cart.getTotalPrice() - orderInsertReq.totalPrice());
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void deletedOrder(OrderDeleteDto orderDeleteDto) {
        orderRepository.deleteOrder(orderDeleteDto.deleteList());
    }

    @Override
    @Transactional
    public void removeOrder(OrderDeleteDto orderDeleteDto) {
        orderRepository.deleteOrder(orderDeleteDto.deleteList());
        productHistoryRepository.deleteProductHistory(orderDeleteDto.deleteList());
    }


    @Override
    public Long findMaxOrderId() {
        return orderRepository.findMaxOrderId();
    }


}
