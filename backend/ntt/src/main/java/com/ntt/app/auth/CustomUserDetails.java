package com.ntt.app.auth;

import com.ntt.app.member.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

/**
 * packageName    : com.ntt.app.auth
 * fileName       : UserPrincipal
 * author         : Kim
 * date           : 2022-10-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-26        Kim       최초 생성
 */
@Builder
public class CustomUserDetails implements UserDetails, OAuth2User {

    @Getter
    private final String id;

    private final List<GrantedAuthority> authorities;

    public static CustomUserDetails create(Member member) {
        return new CustomUserDetails(member.getId(), List.of(() -> "ROLE_USER"));
    }

    public static CustomUserDetails create(String id) {
        return new CustomUserDetails(id, List.of(() -> "ROLE_USER"));
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }

    public String getRedisNotionKey() {
        return "notion-" + this.id;
    }

    public String getRedisTistoryKey() {
        return "tistory-" + this.id;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return this.id;
    }
}
