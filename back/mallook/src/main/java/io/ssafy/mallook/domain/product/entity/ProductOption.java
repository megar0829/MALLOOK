package io.ssafy.mallook.domain.product.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "product_option")
public class ProductOption {
    String color;
    String size;
}
