package io.ssafy.mallook.domain.product.dao;

import io.ssafy.mallook.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
