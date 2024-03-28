package io.ssafy.mallook.domain.product.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class ReviewObject {
    private String contents;
    @Field(name = "created_at")
    private String createdAt;
    List<String> images;
    private Integer point;
    @Field(name = "product_option")
    private List<ProductOption> productOption;
    @Field(name = "user_size")
    private UserSize userSize;

}
