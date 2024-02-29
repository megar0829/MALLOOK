package io.ssafy.mallook.domain.file.service;

import io.ssafy.mallook.domain.file.dao.FileRepository;
import io.ssafy.mallook.domain.file.dto.response.FileRes;
import io.ssafy.mallook.domain.file.entity.File;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import io.ssafy.mallook.global.util.S3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final S3Util s3Util;
    private final FileRepository fileRepository;

    @Override
    public FileRes upload(String path, MultipartFile file) throws IOException {
        return File.toDto(s3Util.upload(path, file));
    }

    @Override
    public void remove(UUID id) {
        s3Util.remove(id);
    }

    @Override
    public File findById(UUID id) {
        return fileRepository.findById(id).orElseThrow(() ->
                new BaseExceptionHandler(ErrorCode.NOT_FOUND_S3FILE));
    }

    @Override
    public Object[] getObject(UUID id) throws IOException {
        var s3File = fileRepository.findById(id).orElseThrow(() ->
                new BaseExceptionHandler(ErrorCode.NOT_FOUND_S3FILE));
        return new Object[]{s3Util.getObject(s3File.getS3DataUrl()),
                s3File.getOriginFileName()};
    }
}