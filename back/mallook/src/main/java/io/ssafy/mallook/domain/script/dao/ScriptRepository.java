package io.ssafy.mallook.domain.script.dao;

import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.entity.Script;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {

    Page<Script> findAllByMember(Member member, Pageable pageable);
}
