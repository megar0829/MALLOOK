package io.ssafy.mallook.domain.product.entity;

public enum MainCategory {
    TOP("상의"),
    BOTTOM("하의"),
    ;
    final String korean;

    MainCategory(String korean) {
        this.korean = korean;
    }
}
