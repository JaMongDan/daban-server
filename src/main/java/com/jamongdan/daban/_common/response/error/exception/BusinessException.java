package com.jamongdan.daban._common.response.error.exception;

import com.jamongdan.daban._common.response.error.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode; // 오류 코드

    @Builder
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
