package com.springenterprise.userservice.domain;

import com.spring.enterprise.common.domain.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}, name = "USER_UNIQUE_USERNAME"))
public class User extends AbstractEntity {

    @NonNull
    @Column(length = 50)
    private String username;

    @NonNull
    @Column
    private String password;

    @NonNull
    @Column(name = "account_expired")
    private Boolean accountExpired;

    @NonNull
    @Column(name = "account_locked")
    private Boolean accountLocked;

    @NonNull
    @Column(name = "credentials_expired")
    private Boolean credentialsExpired;

    @NonNull
    @Column
    private Boolean enabled;

    @Column(name = "activation_key")
    private String activationKey;

    @Column(name = "reset_password_key")
    private String resetPasswordKey;

    @OneToMany(mappedBy = "user", targetEntity = UserAuthority.class, cascade = {
            CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UserAuthority> userAuthorities = new HashSet<>();

}
