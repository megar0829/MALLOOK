package io.ssafy.mallook.domain.style.application;

import io.ssafy.mallook.domain.product.dao.ProductRepository;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.dto.request.StyleInsertReq;
import io.ssafy.mallook.domain.style.dto.response.StyleDetailRes;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.domain.style_product.dao.StyleProductRepository;
import io.ssafy.mallook.domain.style_product.entity.StyleProduct;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StyleServiceImpl implements StyleService{
    private final StyleRepository styleRepository;
    private final StyleProductRepository styleProductRepository;
    private final ProductRepository productRepository;
    @Override
    @Transactional
    public void saveStyle(UUID memberId, StyleInsertReq styleInsertRes) {
        Style style = new Style().builder()
                .name(styleInsertRes.name())
                .heartCount(0L)
                .build();
        var st = styleRepository.save(style);
        List<Long> products = styleInsertRes.productIdList();
        products.stream()
                .map(ele-> styleProductRepository.save(new StyleProduct().builder()
                        .styleId(st)
                        .productId(productRepository.findById(ele).orElseThrow(()->new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR)))
                        .build()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StyleListRes> findStyleList() {
        return styleRepository.findAll().stream()
                .map(ele -> new StyleListRes(ele.getId(), ele.getName())).toList();
    }

    @Override
    public StyleDetailRes findStyleDetail(Long id) {
        var style = styleRepository.findById(id).orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        // product list
        return styleProductRepository.findAllByStyle_Id(id);
        return null;
    }

}
