package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends MongoRepository<Products, String> {
    Products findFirstByOrderById();

    Optional<Products> findFirstByKeywordsIn(List<String> keywords);

}
