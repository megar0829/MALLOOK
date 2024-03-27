package io.ssafy.mallook.domain.product.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
//@Entity

@Document(collection = "products")
public class Products {
    @MongoId
    @Id
    private ObjectId id;
    @Field(name = "main_category")
    private String mainCategory;
    @Field(name = "sub_category")
    private String subCategory;
    private String gender;
    private String name;
    private Long price;
    private List<String> color;
    private List<String> size;
    @Field(name = "brand_name")
    private String brandName;
    private Integer fee;
    private String image;
    private String code;
    private String url;
    @Field(name = "tags")
    private List<String> tags;
    @Field(name = "detail_images")
    private List<String> detailImages;
    @Field(name = "detail_html")
    private String detailHtml;
}
