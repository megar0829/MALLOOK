package io.ssafy.mallook.domain.script.dao;

import io.ssafy.mallook.domain.script.entity.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {
}
