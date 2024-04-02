package io.ssafy.mallook.domain.script.dao;

import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.entity.Script;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {

    @Query("SELECT max (s.id) from Script s")
    Long findMaxId();

    Slice<Script> findByIdLessThanAndMemberOrderByIdDesc(Long id, Member member, Pageable pageable);

    Slice<Script> findByIdLessThanOrderByIdDesc(Long id, Pageable pageable);

    Optional<Script> findTopByMemberOrderByIdDesc(Member member);

    @Query("SELECT s FROM Script s ORDER BY s.totalLike DESC limit 50")
    List<Script> findTop50ScriptsOrderByTotalLikeDesc();

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Script s set s.status = false where s.id in :deleteList and s.status = true ")
    void deleteScript(@Param("deleteList") List<Long> deleteList);

    @Modifying
    @Query("UPDATE Script s SET s.totalLike = 0")
    void resetAllTotalLike();
}
