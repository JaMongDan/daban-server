package com.jamongdan.daban._common.socialaccount.entity;

import com.jamongdan.daban._common.entity.BaseTime;
import com.jamongdan.daban.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "social_accounts", uniqueConstraints = {@UniqueConstraint(columnNames = {"provider", "providerId"})})
@SQLDelete(sql = "UPDATE social_accounts SET deleted_at = NOW() where social_account_id = ?")
public class SocialAccount extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_account_id")
    private Long socialAccountId;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public SocialAccount(String provider, String providerId, User user) {
        this.provider = provider;
        this.providerId = providerId;
        this.user = user;
    }
}
