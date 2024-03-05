package io.ssafy.mallook.domain.heart.dao;


import io.ssafy.mallook.domain.heart.entity.Heart;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.entity.Script;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {

    Page<Heart> findAllByMember(Member member, Pageable pageable);
    void deleteByMemberAndScript(Member member, Script script);
}
