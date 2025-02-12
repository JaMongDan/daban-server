package com.jamongdan.daban._common.oauth.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Naver OAuth 2.0 인증 후 반환되는 JSON 데이터를 처리하는 dto
 */
public class KakaoResponse implements OAuth2Response {

    private static final String ID = "id";
    private static final String NICKNAME = "nickname";
    private final Map<String, Object> attribute;

    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = new HashMap<>();

        Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        String id = String.valueOf(attribute.get(ID)); // id가 Long 타입이므로 String.valueOf 사용

        this.attribute.put(ID, id);
        this.attribute.put(NICKNAME, profile.get(NICKNAME));
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get(ID).toString();
    }

    @Override
    public String getName() {
        return attribute.get(NICKNAME).toString();
    }
}
