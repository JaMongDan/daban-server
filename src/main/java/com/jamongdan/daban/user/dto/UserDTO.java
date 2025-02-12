package com.jamongdan.daban.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String providerId; // 고유 식별자
    private String name; // 사용자 이름
    private String role; // 사용자 역할
}
