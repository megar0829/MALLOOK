package io.ssafy.mallook.domain.product.entity;

public enum SubCategory {
    SPORT("스포츠류"),
    FORMAL("정장"),
    ;
    final String korean;
    SubCategory(String korean) {this.korean = korean;}
}
