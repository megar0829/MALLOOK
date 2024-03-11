package io.ssafy.mallook.domain.script.dao;

import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.entity.Script;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {

    Page<Script> findAllByMember(Member member, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Script s set s.status = false where s.id in :deleteList and s.status = true ")
    void deleteScript(@Param("deleteList") List<Long> deleteList);
}
