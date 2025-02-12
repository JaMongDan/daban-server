package com.jamongdan.daban._common.oauth.dto;

/**
 * OAuth2 제공자 별로 리턴하는 사용자 정보 데이터의 구조와 필드 이름이 다름. 이를 통합하기 위한 인터페이스
 */
public interface OAuth2Response {

    String getProvider();

    String getProviderId();

    String getName();
}
