package com.jamongdan.daban.user.entity;

import com.jamongdan.daban._common.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() where user_id = ?")
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;

    @Builder
    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }
}

