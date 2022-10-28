package com.ntt.app.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.ntt.app.auth
 * fileName       : NotionOAuth2User
 * author         : Kim
 * date           : 2022-10-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-27        Kim       최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class NotionOAuth2User implements OAuth2User {

    private List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
    private Map<String, Object> attributes;
    private String id;
    private String name;
    private String avatar_url;
    private Person person;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public class Person {
        private String email;
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        if(this.attributes == null) {
            this.attributes = new HashMap<>();
            this.attributes.put("id", this.getId());
            this.attributes.put("name", this.getName());
            this.attributes.put("avatar_url", this.getAvatar_url());
            this.attributes.put("email", this.getPerson().getEmail());
        }
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return null;
    }
}
