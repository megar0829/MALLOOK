package io.ssafy.mallook.domain.style.application;

import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.dao.ProductRepository;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.dto.request.StyleInsertReq;
import io.ssafy.mallook.domain.style.dto.response.StyleDetailRes;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import io.ssafy.mallook.domain.style.dto.response.StylePageRes;
import io.ssafy.mallook.domain.style.dto.response.StyleProductRes;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.domain.style_product.dao.StyleProductRepository;
import io.ssafy.mallook.domain.style_product.entity.StyleProduct;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StyleServiceImpl implements StyleService{
    private final StyleRepository styleRepository;
    private final StyleProductRepository styleProductRepository;
    @Override
    @Transactional
    public void saveStyle(UUID memberId, StyleInsertReq styleInsertRes) {
        Style style = new Style().builder()
                .name(styleInsertRes.name())
                .heartCount(0L)
                .member(new Member(memberId))
                .build();
        var st = styleRepository.save(style);
        styleInsertRes.productIdList().forEach(ele ->
                styleProductRepository.save(
                        new StyleProduct().builder()
                                .style(st)
                                .product(new Product().builder().id(ele).build())
                                .build()));
    }

    @Override
    @Transactional(readOnly = true)
    public StylePageRes findStyleList(Pageable pageable) {
        var result = styleRepository.findAll(pageable);
        return new StylePageRes(
                result.getContent().stream().map(ele -> new StyleListRes(ele.getId(), ele.getName())).toList(),
                result.getTotalPages(),
                result.getNumber()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public StyleDetailRes findStyleDetail(Long id) {
        var style = styleRepository.findById(id).orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        return new StyleDetailRes(
                style.getMember().getNickname(),
                style.getName(),
                style.getHeartCount(),
                style.getStyleProductList().stream().map(ele -> new StyleProductRes(
                        ele.getProduct().getName(),
                        ele.getProduct().getPrice(),
                        ele.getProduct().getBrandName(),
                        ele.getProduct().getImage()
                )).toList()
        );
    }

    @Transactional
    @Override
    public void DeleteStyle(UUID memberId, Long styleId) {
        styleProductRepository.deleteMyStyleProduct(styleId);
        styleRepository.deleteMyStyle(memberId, styleId);
    }
}
