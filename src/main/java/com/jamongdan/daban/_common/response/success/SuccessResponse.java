package com.jamongdan.daban._common.response.success;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse<T> {

    private final HttpStatus status;  // 응답 상태

    private final String message; // 응답 메시지

    private final T data; // 반환 데이터

    @Builder
    private SuccessResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static SuccessResponse<Void> of(SuccessCode successType) {
        return SuccessResponse.<Void>builder()
                .status(successType.getStatus())
                .message(successType.getMessage())
                .build();
    }

    public static <T> SuccessResponse<T> of(SuccessCode successType, T data) {
        return SuccessResponse.<T>builder()
                .status(successType.getStatus())
                .message(successType.getMessage())
                .data(data)
                .build();
    }
}
