package io.ssafy.mallook.domain.product.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "reviews")
@Getter
public class Reviews {
    @Field(name = "count")
    private Integer count;
    @Field(name = "average_point")
    private Double averagePoint;
    @Field(name = "reviews")
    private List<ReviewObject> reviews;

}
