package com.jamongdan.daban._common.oauth.dto;

import java.util.Map;

/**
 * Naver OAuth 2.0 인증 후 반환되는 JSON 데이터를 처리하는 dto
 */
public class NaverResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public NaverResponse(Map<String, Object> attribute) {
        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}
