package io.ssafy.mallook.domain.file.dao;

import io.ssafy.mallook.domain.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
}
