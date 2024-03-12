package io.ssafy.mallook.domain.style_product.dao;

import io.ssafy.mallook.domain.style_product.entity.StyleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface StyleProductRepository extends JpaRepository<StyleProduct, Long> {
    @Modifying(clearAutomatically = true)
    @Query(value = """
        update StyleProduct s
        set s.status = false
        where s.style.id in :styleProductIdList
        """
    )
    void deleteMyStyleProduct(@Param("styleProductIdList") List<Long> styleProductIdList);

}
