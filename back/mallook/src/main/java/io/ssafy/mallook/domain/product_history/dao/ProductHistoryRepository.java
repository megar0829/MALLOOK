package io.ssafy.mallook.domain.product_history.dao;

import io.ssafy.mallook.domain.product_history.entity.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {
    @Modifying(clearAutomatically = true)
    @Query("""
            update ProductHistory pd
            set pd.status = false
            where pd.orders.id in :deleteList and pd.status = true
            """)
    void deleteProductHistory(@Param("deleteList") List<Long> deleteList);

}
