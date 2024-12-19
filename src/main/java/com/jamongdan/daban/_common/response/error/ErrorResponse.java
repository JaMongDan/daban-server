package com.jamongdan.daban._common.response.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    private final HttpStatus status;  // 오류 상태

    private final String message; // 오류 메시지

    private String reason; // 오류 이유

    @Builder
    protected ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
    }

    @Builder
    protected ErrorResponse(ErrorCode code, String reason) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.reason = reason;
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(ErrorCode code, String reason) {
        return new ErrorResponse(code, reason);
    }
}
