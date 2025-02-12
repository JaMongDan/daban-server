package com.jamongdan.daban._common.oauth.service;

import com.jamongdan.daban._common.oauth.dto.*;
import com.jamongdan.daban._common.socialaccount.entity.SocialAccount;
import com.jamongdan.daban._common.socialaccount.repository.SocialAccountRepository;
import com.jamongdan.daban.user.dto.UserDTO;
import com.jamongdan.daban.user.entity.User;
import com.jamongdan.daban.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * OAuth 2.0 인증 후 사용자 정보를 처리하는 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final SocialAccountRepository socialAccountRepository;

    /**
     * OAuth2 로그인 처리 메서드
     * - OAuth2 리소스 서버(Google, Kakao, Naver)에서 사용자 정보를 가져온다.
     * - 기존 회원인지 확인하고, 없으면 User 및 SocialAccount를 생성하여 DB에 저장한다.
     * - 인증된 사용자 정보를 CustomOAuth2User로 반환한다.
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2UserResponse = null;

        if (registrationId.equals("naver")) { // 각각의 리소스 서버가 제공해주는 데이터 형태가 다르기 때문에 전처리 과정이 필요
            oAuth2UserResponse = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {
            oAuth2UserResponse = new KakaoResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2UserResponse = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String provider = oAuth2UserResponse.getProvider();
        String providerId = oAuth2UserResponse.getProviderId();
        Optional<SocialAccount> existData = socialAccountRepository.findByProviderAndProviderId(provider, providerId); // 해당 유저가 이미 존재하는 지 확인
        log.debug("");

        log.info("Provider: {}, ProviderId: {}, Name: {}", provider, providerId, oAuth2UserResponse.getName());

        if (existData.isEmpty()) { // 존재하지 않는 경우 새로운 사용자로 등록함
            User user = userRepository.save(
                    User.builder()
                            .name(oAuth2UserResponse.getName())
                            .role("USER")
                            .build()
            );

            socialAccountRepository.save(
                    SocialAccount.builder()
                            .provider(provider)
                            .providerId(providerId)
                            .user(user)
                            .build()
            );
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setProviderId(providerId);
        userDTO.setName(oAuth2UserResponse.getName());
        userDTO.setRole("USER");

        return new CustomOAuth2User(userDTO);
    }
}
