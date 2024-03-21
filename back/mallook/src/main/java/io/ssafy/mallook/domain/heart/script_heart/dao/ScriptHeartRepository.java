package io.ssafy.mallook.domain.heart.script_heart.dao;


import io.ssafy.mallook.domain.heart.script_heart.entity.ScriptHeart;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.domain.style.entity.Style;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScriptHeartRepository extends JpaRepository<ScriptHeart, Long> {

    Slice<ScriptHeart> findByIdLessThanAndMemberOrderByIdDesc(Long id, Member member, Pageable pageable);

    Optional<ScriptHeart> findByMemberAndScript(Member member, Script script);

    @Query("SELECT max (h.id) from ScriptHeart h")
    Long findMaxHeartId();
}
