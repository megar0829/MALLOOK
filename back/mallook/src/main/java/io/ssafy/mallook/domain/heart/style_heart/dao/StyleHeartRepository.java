package io.ssafy.mallook.domain.heart.style_heart.dao;


import io.ssafy.mallook.domain.heart.style_heart.entity.StyleHeart;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.style.entity.Style;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleHeartRepository extends JpaRepository<StyleHeart, Long> {

    Slice<StyleHeart> findByIdLessThanAndMemberOrderByIdDesc(Long id, Member member, Pageable pageable);

    Optional<StyleHeart> findByMemberAndStyle(Member member, Style style);

    @Query("SELECT max (h.id) from ScriptHeart h")
    Long findMaxHeartId();
}
