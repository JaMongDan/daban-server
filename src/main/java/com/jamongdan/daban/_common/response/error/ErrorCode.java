package com.jamongdan.daban._common.response.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 400 : Bad Request           잘못된 요청 (입력값 검증 실패, 유효하지 않은 데이터)
 * 401 : Unauthorized          인증되지 않음 (로그인 필요, 토큰 누락/유효하지 않음)
 * 403 : Forbidden             권한 부족 (인증은 되었지만 권한이 없음)
 * 404 : Not Found             요청한 리소스를 찾을 수 없음
 * 500 : Internal Server Error 서버 내부 오류 (예상치 못한 예외 발생)
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * GLOBAL ERROR
     */
    MISSING_REQUEST_PARAMETER_ERROR(HttpStatus.BAD_REQUEST, "Request Parameter로 데이터가 전달되지 않았습니다."),
    PATH_VARIABLE_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, "요청 경로의 Path Variable이 누락되었습니다."),
    REQUEST_BODY_MISSING_ERROR(HttpStatus.BAD_REQUEST, "@RequestBody 데이터가 누락되었습니다."),
    NOT_VALID_HEADER_ERROR(HttpStatus.BAD_REQUEST, "Header에 데이터가 존재하지 않습니다."),
    JACKSON_PROCESS_ERROR(HttpStatus.BAD_REQUEST, "Jackson 처리 오류 발생했습니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "유효하지 않은 데이터 타입입니다."),
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "잘못된 서버 요청입니다."),
    JSON_PARSE_ERROR(HttpStatus.BAD_REQUEST, "JSON 파싱 오류 발생했습니다."),
    NOT_VALID_ERROR(HttpStatus.BAD_REQUEST, "유효하지 않은 요청 데이터입니다."),
    IO_ERROR(HttpStatus.BAD_REQUEST, "입출력 오류가 발생했습니다."),
    FORBIDDEN_ERROR(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),
    METHOD_NOT_SUPPORTED_ERROR(HttpStatus.METHOD_NOT_ALLOWED, "지원되지 않는 HTTP 메서드로 요청했습니다."),

    NULL_POINT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "NULL 포인터 예외가 발생했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),

    ;

    private final HttpStatus status; // 응답 상태

    private final String message; // 응답 메시지
}
