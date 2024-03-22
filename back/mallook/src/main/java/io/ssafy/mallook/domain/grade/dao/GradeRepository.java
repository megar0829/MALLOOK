package io.ssafy.mallook.domain.grade.dao;

import io.ssafy.mallook.domain.grade.entity.Grade;
import io.ssafy.mallook.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findByMember(Member member);
}
