package com.ntt.app.auth;

import com.ntt.app.user.User;
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
public class UserPrincipal implements UserDetails, OAuth2User {

    private final User user;
    private final SocialType socialType;
    private final List<GrantedAuthority> authorities;
    private final Map<String, Object> oauthUserAttributes;

    private UserPrincipal(User user, SocialType socialType, List<GrantedAuthority> authorities, Map<String, Object> oauthUserAttributes) {
        this.user = user;
        this.socialType = socialType;
        this.authorities = authorities;
        this.oauthUserAttributes = oauthUserAttributes;
    }

    public static UserPrincipal create(User user, SocialType socialType, Map<String, Object> oauthUserAttributes) {
        return new UserPrincipal(user, socialType, List.of(() -> "ROLE_USER"), oauthUserAttributes);
    }

    public static UserPrincipal create(User user) {
        return create(user, SocialType.NONE, new HashMap<>());
    }

    public SocialType getSocialType() {
        return this.socialType;
    }

    @Override
    public String getName() {
        return user.getEmail();
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(oauthUserAttributes);
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
        return user.getEmail();
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
