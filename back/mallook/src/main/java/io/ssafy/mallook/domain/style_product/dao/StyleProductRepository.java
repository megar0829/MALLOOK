package io.ssafy.mallook.domain.style_product.dao;

import io.ssafy.mallook.domain.style_product.entity.StyleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StyleProductRepository extends JpaRepository<StyleProduct, Long> {
    List<StyleProduct> findAllByStyle_Id(Long id);
}
