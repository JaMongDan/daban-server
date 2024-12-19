package com.jamongdan.daban._common.response.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 200 : OK                    요청이 성공적으로 처리됨 (SELECT, 일반 응답)
 * 201 : Created               요청이 성공적으로 처리되어 리소스가 생성됨 (INSERT)
 * 202 : Accepted              요청이 수락되었으나 처리가 아직 완료되지 않음 (비동기 작업)
 * 204 : No Content            요청이 성공적으로 처리되었으나 반환할 콘텐츠가 없음 (UPDATE, DELETE)
 * 206 : Partial Content       클라이언트의 범위 요청에 따라 부분 데이터 반환 (파일 다운로드 등)
 */
@Getter
@AllArgsConstructor
public enum SuccessCode {

    ;

    private final HttpStatus status; // 응답 상태

    private final String message; // 응답 메시지
}
