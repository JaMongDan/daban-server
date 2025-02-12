package com.jamongdan.daban._common.config;

import com.jamongdan.daban._common.oauth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http // 보안 설정
                .csrf(auth -> auth.disable()) // csrf 비활성화
                .formLogin(auth -> auth.disable()) // form 로그인 방식 비활성화
                .httpBasic(auth -> auth.disable()) // HTTP 기본 인증 방식 비활성화
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)));

        http // URL 기반 접근 제어
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login/oauth2/**").permitAll() // 누구나 접근 가능
                        .anyRequest().authenticated()); // 로그인한 사용자만 가능

        http // 세션 관리 설정
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //jwt를 사용하기 때문에 stateless로 설정

        return http.build();
    }
}
