package com.ntt.app.auth;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

import static com.ntt.app.auth.NotionParameterKeys.JSON_KEY_USER_ID;

/**
 * packageName    : com.ntt.app.auth
 * fileName       : OAuth2UserRequestEntityCustomConverter
 * author         : Kim
 * date           : 2022-10-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-28        Kim       최초 생성
 */
@RequiredArgsConstructor
public class OAuth2UserRequestEntityCustomConverter implements Converter<OAuth2UserRequest, RequestEntity<?>> {

    private final String userId;

    @Override
    public RequestEntity<?> convert(OAuth2UserRequest userRequest) {
        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        HttpMethod httpMethod = getHttpMethod(clientRegistration);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(userRequest.getAccessToken().getTokenValue());
        // notion은 꼭 version을 헤더에 명시해줘야 함
        headers.set("Notion-Version", "2022-06-28");

        URI uri = UriComponentsBuilder
                .fromUriString(clientRegistration.getProviderDetails().getUserInfoEndpoint().getUri() + userId).build().toUri();

        return new RequestEntity<>(headers, httpMethod, uri);
    }

    private HttpMethod getHttpMethod(ClientRegistration clientRegistration) {
        return HttpMethod.GET; // Notion
    }
}
