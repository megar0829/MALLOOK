package io.ssafy.mallook.domain.style.dao;

import io.ssafy.mallook.domain.style.entity.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface StyleRepository extends JpaRepository<Style, Long> {
    Page<Style> findAll(Pageable pageable);
    @Modifying
    @Query(value = """
        update Style s
        set s.status = false
        where s.id = :styleId and s.member.id = :memberId and s.status = true
        """
    )
    void deleteMyStyle(@Param("memberId") UUID memberId, @Param("styleId") Long styleId);
    }
