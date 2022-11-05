package com.ntt.app.auth;

import com.ntt.app.user.Member;
import com.ntt.app.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.ntt.app.auth.NotionParameterKeys.*;

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

        Map<String, Object> additionalParameters = userRequest.getAdditionalParameters();

        // Json 포멧도 아닌 이상한 포멧으로 데이터가 들어와서...id만 골라냈음
        String userId = getUserId(additionalParameters.get(JSON_KEY_OWNER).toString());

        // 커스텀 컨버터를 만들어서 Notion Retrieve a user api 용 Request Entity를 생성하였음
        super.setRequestEntityConverter(new OAuth2UserRequestEntityCustomConverter(userId));

        // DefaultOAuth2UserService를 통해서 loadUser 수행
        OAuth2User user = super.loadUser(userRequest);

        return process(user);
    }

    /*
    * 1. 자동 가입 처리
    * 2. 업데이트 처리
    * */
    @Transactional
    public CustomUserDetails process(OAuth2User oAuth2User) {

        Member member = memberRepository
                        .findById(oAuth2User.getName())
                        .orElseGet(() -> createUser(oAuth2User));

        if(!StringUtils.equals(oAuth2User.getAttribute("name"), member.getName())) {
            member.setName(oAuth2User.getAttribute("name"));
        }
        if(!StringUtils.equals(oAuth2User.getAttribute("avatar_url"), member.getAvatarUrl())) {
            member.setAvatarUrl(oAuth2User.getAttribute("avatar_url"));
        }

        return CustomUserDetails.create(member);
    }

    private Member createUser(OAuth2User oAuth2User) {
        return memberRepository.save(
                Member.builder()
                        .id(oAuth2User.getAttribute("id"))
                        .name(oAuth2User.getAttribute("name"))
                        .avatarUrl(oAuth2User.getAttribute("avatar_url"))
                        .build()
        );
    }

    private String getUserId(String badJsonString) {
        int fromIdx = badJsonString.indexOf("id") + 3;
        int toIdx = badJsonString.indexOf(',', fromIdx);
        return badJsonString.substring(fromIdx, toIdx);
    }
}
