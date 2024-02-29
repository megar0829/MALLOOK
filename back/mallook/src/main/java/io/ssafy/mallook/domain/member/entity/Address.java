package io.ssafy.mallook.domain.member.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String city; // 시
    private String district; // 구
    private String address; // 상세주소
    private String zipcode; // 우편번호
}