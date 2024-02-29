package io.ssafy.mallook.global.exception;

import io.ssafy.mallook.global.common.code.ErrorCode;
import lombok.Getter;

@Getter
public class BaseExceptionHandler extends RuntimeException {
    private final ErrorCode errorCode;

    public BaseExceptionHandler(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BaseExceptionHandler(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
