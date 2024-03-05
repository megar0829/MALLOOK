package io.ssafy.mallook.domain.style.dao;

import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import io.ssafy.mallook.domain.style.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface StyleRepository extends JpaRepository<Style, Long> {
    List<Style> findAll();
}
