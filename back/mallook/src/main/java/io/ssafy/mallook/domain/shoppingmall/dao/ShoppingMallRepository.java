package io.ssafy.mallook.domain.shoppingmall.dao;

import io.ssafy.mallook.domain.shoppingmall.entity.ShoppingMall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingMallRepository extends JpaRepository<ShoppingMall, Long> {

    @Query("SELECT max (m.id) from ShoppingMall m")
    Long findMaxId();
}
