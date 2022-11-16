package com.ntt.app.auth;

import com.ntt.app.member.Member;
import com.ntt.app.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.ntt.app.auth
 * fileName       : CustomOAuth2UserService
 * author         : Kim
 * date           : 2022-10-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-26        Kim       최초 생성
 */
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // http://localhost:8080/oauth2/authorization/authclient

        OAuth2User user = super.loadUser(userRequest);
        System.out.println(user);
        System.out.println(user.getAttributes().get("login"));
        System.out.println(user.getAttributes().get("id"));
        System.out.println(user.getAttributes().get("avatar_url"));
        System.out.println(user.getAttributes().get("html_url"));

        return process(user);
    }

    /*
    * 1. 자동 가입 처리
    * 2. 업데이트 처리
    * */
    private CustomUserDetails process(OAuth2User oAuth2User) {

        Member member = memberRepository
                        .findById(oAuth2User.getAttributes().get("id").toString())
                        .orElseGet(() -> createUser(oAuth2User));

        update(oAuth2User, member);

        memberRepository.save(member); // 변경 감지 사용안함

        return CustomUserDetails.create(member);
    }

    private void update(OAuth2User oAuth2User, Member member) {
        if(!StringUtils.equals(oAuth2User.getAttributes().get("name").toString(), member.getName())) {
            member.setName(oAuth2User.getAttributes().get("name").toString());
        }
        if(!StringUtils.equals(oAuth2User.getAttributes().get("avatar_url").toString(), member.getAvatar())) {
            member.setAvatar(oAuth2User.getAttributes().get("avatar_url").toString());
        }
        if(!StringUtils.equals(oAuth2User.getAttributes().get("html_url").toString(), member.getGithub())) {
            member.setAvatar(oAuth2User.getAttributes().get("html_url").toString());
        }
    }

    private Member createUser(OAuth2User oAuth2User) {
        return memberRepository.save(
                Member.builder()
                        .id(oAuth2User.getAttributes().get("id").toString())
                        .name(oAuth2User.getAttributes().get("name").toString())
                        .avatar(oAuth2User.getAttributes().get("avatar_url").toString())
                        .github(oAuth2User.getAttributes().get("html_url").toString())
                        .build()
        );
    }
}
