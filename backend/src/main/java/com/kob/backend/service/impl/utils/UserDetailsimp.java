package com.kob.backend.service.impl.utils;

import com.kob.backend.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Author: chen
 * Time: 2024/1/29 0:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsimp implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {      //是否过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {       //是否被锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {      //授权是否过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
