package io.ssafy.mallook.domain.product.dao;

import io.ssafy.mallook.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT max (p.id) from Product p")
    Long findMaxId();
}
