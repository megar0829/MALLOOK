package io.ssafy.mallook.domain.style.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.dto.request.StyleInsertReq;
import io.ssafy.mallook.domain.style.dto.response.StyleDetailRes;
import io.ssafy.mallook.domain.style.dto.response.StyleProductRes;
import io.ssafy.mallook.domain.style.dto.response.StyleRes;
import io.ssafy.mallook.domain.style.dto.response.StyledWorldCupDto;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.domain.style_product.dao.StyleProductRepository;
import io.ssafy.mallook.domain.style_product.entity.StyleProduct;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StyleServiceImpl implements StyleService {

    private final MemberRepository memberRepository;
    private final StyleRepository styleRepository;
    private final StyleProductRepository styleProductRepository;

    @Transactional(readOnly = true)
    @Override
    public Slice<StyleRes> findStyleListFirst(Pageable pageable) {
        Long maxId = styleRepository.findMaxId();
        return styleRepository.findStylesByIdLessThan(pageable, maxId + 1);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<StyleRes> findStyleList(Pageable pageable, Long cursor) {
        return styleRepository.findStylesByIdLessThan(pageable, cursor + 1);
    }

    @Override
    public List<StyledWorldCupDto> getWorldCupList(UUID id) {
        Member proxyMember = memberRepository.getReferenceById(id);
        List<Style> top50StyleList = styleRepository.findTop50StylesWithDifferentMembersOrderByTotalLikeDesc(proxyMember);
        Collections.shuffle(top50StyleList);

        return top50StyleList.subList(0, 8)
                .stream()
                .map(StyledWorldCupDto::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StyleDetailRes findStyleDetail(Long id) {
        var style = styleRepository.findById(id).orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
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

    @Override
    @Transactional
    public void saveStyle(UUID memberId, StyleInsertReq styleInsertRes) {
        Style style = Style.builder()
                .name(styleInsertRes.name())
                .heartCount(0L)
                .totalLike(0)
                .member(new Member(memberId))
                .build();
        var st = styleRepository.save(style);
        styleInsertRes.productIdList().forEach(ele ->
                styleProductRepository.save(
                        StyleProduct.builder()
                                .style(st)
                                .product(Product.builder().id(ele).build())
                                .build()));
    }

    @Transactional
    @Override
    public void DeleteStyle(UUID memberId, List<Long> styleIdList) {
        styleRepository.deleteMyStyle(memberId, styleIdList);
    }
}
