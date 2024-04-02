package io.ssafy.mallook.domain.style.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.dao.mongo.ProductsCustomRepository;
import io.ssafy.mallook.domain.product.dao.mongo.ProductsRepository;
import io.ssafy.mallook.domain.product.dto.response.ProductImgRes;
import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.dto.request.StyleInsertReq;
import io.ssafy.mallook.domain.style.dto.response.StyleDetailRes;
import io.ssafy.mallook.domain.style.dto.response.StyleProductRes;
import io.ssafy.mallook.domain.style.dto.response.StyleRes;
import io.ssafy.mallook.domain.style.dto.response.StyledWorldCupDto;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.domain.style_product.dao.StyleProductRepository;
import io.ssafy.mallook.domain.style_product.entity.StyleProduct;
import io.ssafy.mallook.global.batch.dao.Top50RedisDao;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static io.ssafy.mallook.global.common.code.ErrorCode.NOT_FOUND_PRODUCT;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StyleServiceImpl implements StyleService {

    private final MemberRepository memberRepository;
    private final StyleRepository styleRepository;
    private final StyleProductRepository styleProductRepository;
    private final ProductsRepository productsRepository;
    private final Top50RedisDao top50RedisDao;
    private final ProductsCustomRepository productsCustomRepository;

    @Override
    public Slice<StyleRes> findStyleListFirst(Pageable pageable) {
        Long maxId = styleRepository.findMaxId();
        // Style 객체의 Slice를 조회
        Slice<Style> styleSlice = styleRepository.findStylesByIdLessThan(pageable, maxId + 1);

        // Style 객체를 StyleRes 객체로 변환
        List<StyleRes> convertedList = styleSlice.getContent().stream()
                .map(this::toResDto) // Style -> StyleRes 변환
                .collect(toList());

        // 변환된 List<StyleRes>를 사용하여 새로운 Slice<StyleRes> 생성
        return new SliceImpl<>(convertedList, pageable, styleSlice.hasNext());
    }

    @Override
    public Slice<StyleRes> findStyleList(Pageable pageable, Long cursor) {
        // Style 객체의 Slice를 조회
        Slice<Style> styleSlice = styleRepository.findStylesByIdLessThan(pageable, cursor + 1);

        // Style 객체를 StyleRes 객체로 변환
        List<StyleRes> convertedList = styleSlice.getContent().stream()
                .map(this::toResDto) // Style -> StyleRes 변환
                .collect(toList());

        // 변환된 List<StyleRes>를 사용하여 새로운 Slice<StyleRes> 생성
        return new SliceImpl<>(convertedList, pageable, styleSlice.hasNext());
    }

    @Override
    public List<StyledWorldCupDto> getWorldCupList() {
        List<Long> topStylePkList = top50RedisDao.getStylesDto().styleIdList();
        List<Style> top50StyleList = styleRepository.findAllById(topStylePkList);
        Collections.shuffle(top50StyleList);

        return top50StyleList.stream()
                .map(this::toDto)
                .limit(8)
                .toList();
    }

    @Override
    public StyleDetailRes findStyleDetail(Long id) {
        var style = styleRepository.findById(id)
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        List<StyleProductRes> productList = style.getStyleProductList().stream()
                .map(ele -> productsRepository.findById(ele.getProducts())
                        .map(product -> new StyleProductRes(
                                product.getName(),
                                product.getPrice(),
                                product.getBrandName(),
                                product.getImage()))
                        .orElseThrow(() -> new BaseExceptionHandler(NOT_FOUND_PRODUCT)))
                .collect(toList());

        return StyleDetailRes.builder()
                .styleProductResList(productList)
                .heartCount(style.getHeartCount())
                .name(style.getName())
                .imageUrl(style.getImgUrl())
                .build();
    }

    @Override
    @Transactional
    public void saveStyle(UUID memberId, StyleInsertReq styleInsertReq) {
        Member proxyMember = memberRepository.getReferenceById(memberId);
        Style style = Style.builder()
                .name(styleInsertReq.name())
                .heartCount(0L)
                .totalLike(0)
                .member(proxyMember)
                .imgUrl(styleInsertReq.imageUrl())
                .build();
        var st = styleRepository.save(style);
        styleInsertReq.productIdList().forEach(ele ->
                styleProductRepository.save(
                        StyleProduct.builder()
                                .style(st)
                                .products(ele)
                                .build()));
    }

    @Override
    @Transactional
    public void DeleteStyle(UUID memberId, List<Long> styleIdList) {
        styleRepository.deleteMyStyle(memberId, styleIdList);
    }

    @Override
    public Page<ProductImgRes> getMallookBookImages(Pageable pageable, String mainCategory, String subCategory) {
        return productsCustomRepository.getProductImg(pageable, mainCategory, subCategory);
    }

    public StyledWorldCupDto toDto(Style style) {
        return StyledWorldCupDto.builder()
                .id(style.getId())
                .name(style.getName())
                .heartCount(style.getHeartCount())
                .memberNickname(style.getMember().getNickname())
                .imageUrl(style.getImgUrl())
                .keywordList(style.getStyleProductList()
                        .stream()
                        .flatMap(ele -> productsRepository.findById(ele.getProducts())
                                .map(product -> product.getKeywords().stream())
                                .orElseThrow(() -> new BaseExceptionHandler(NOT_FOUND_PRODUCT)))
                        .collect(toCollection(LinkedHashSet::new))
                        .stream()
                        .limit(5)
                        .collect(toList()))
                .build();
    }

    public StyleRes toResDto(Style style) {
        List<ProductsListDto> productsListDtoList = new ArrayList<>();

        for (StyleProduct styleProduct : style.getStyleProductList()) {
            // StyleProduct의 product 필드로 MongoDB에서 Products 객체를 조회
            productsRepository.findById(styleProduct.getProducts()).ifPresent(products -> {
                // 조회된 Products 객체를 ProductsListDto로 변환하여 목록에 추가
                productsListDtoList.add(ProductsListDto.toDto(products));
            });
        }

        return StyleRes.builder()
                .id(style.getId())
                .name(style.getName())
                .heartCount(style.getHeartCount())
                .memberNickname(style.getMember().getNickname())
                .productsListDtoList(productsListDtoList)
                .build();
    }
}
