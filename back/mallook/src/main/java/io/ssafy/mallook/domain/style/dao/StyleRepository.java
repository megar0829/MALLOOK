package io.ssafy.mallook.domain.style.dao;

import io.ssafy.mallook.domain.style.entity.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;

public interface StyleRepository extends JpaRepository<Style, Long> {
    Page<Style> findAll(Pageable pageable);
    @Modifying(clearAutomatically = true)
    @Query(value = """
        update Style s
        set s.status = false
        where s.id in :styleIdList and s.member.id = :memberId
        """
    )
    void deleteMyStyle(@Param("memberId") UUID memberId, @Param("styleIdList") List<Long> styleIdList);
    }