package com.ntt.app.auth;

import com.ntt.app.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;
    private final List<GrantedAuthority> authorities;
    private final Map<String, Object> oauthUserAttributes;

    public static CustomUserDetails create(User user, Map<String, Object> oauthUserAttributes) {
        return new CustomUserDetails(user, List.of(() -> "ROLE_USER"), oauthUserAttributes);
    }

    public static CustomUserDetails create(User user) {
        return create(user, new HashMap<>());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId().toString();
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
}
