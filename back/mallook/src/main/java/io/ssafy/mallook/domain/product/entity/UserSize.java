package io.ssafy.mallook.domain.product.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "user_size")
public class UserSize {
    String height;
    String weight;
}
