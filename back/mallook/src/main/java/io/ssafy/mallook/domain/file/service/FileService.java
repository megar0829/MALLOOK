package io.ssafy.mallook.domain.file.service;

import io.ssafy.mallook.domain.file.dto.response.FileRes;
import io.ssafy.mallook.domain.file.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface FileService {
    FileRes upload(String path, MultipartFile file) throws IOException;

    void remove(UUID id);

    File findById(UUID id);

    Object[] getObject(UUID id) throws IOException;
}