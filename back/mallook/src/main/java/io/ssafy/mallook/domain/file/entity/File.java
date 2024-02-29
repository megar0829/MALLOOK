package io.ssafy.mallook.domain.file.entity;

import io.ssafy.mallook.domain.file.dto.response.FileRes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class File {
    @Id
    @Column(name = "s3file_id")
    private UUID id;
    private String originFileName; // 파일 원본 이름
    private String fileName; // 변환된 파일명
    private String s3DataUrl; // 파일 링크
    private String fileDir; // S3 파일 경로

    @Builder
    public File(UUID id, String originFileName, String fileName, String s3DataUrl, String fileDir) {
        this.id = id;
        this.fileName = fileName;
        this.originFileName = originFileName;
        this.s3DataUrl = s3DataUrl;
        this.fileDir = fileDir;
    }

    public static FileRes toDto(File file) {
        return new FileRes(
                file.getId(),
                file.getOriginFileName(),
                file.getFileName(),
                file.getS3DataUrl(),
                file.getS3DataUrl()
        );
    }
}