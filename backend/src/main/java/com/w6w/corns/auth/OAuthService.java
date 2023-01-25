package com.w6w.corns.auth;

import com.w6w.corns.user.domain.User;
import com.w6w.corns.user.domain.UserRepository;
import com.w6w.corns.user.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final InMemoryProviderRepository inMemoryProviderRepository;
    private final UserRepository userRepository;

    public LoginResponseDto login(String providerName, String code) {
        //OauthProvider 가져오기
        OauthProvider provider = inMemoryProviderRepository.findByProviderName(providerName);

        //access token 가져오기
        OauthTokenResponseDto tokenResponseDto = getToken(code, provider);

        //유저 정보 가져오기
        UserProfileDto userProfileDto = getUserProfile(providerName, tokenResponseDto, provider);


        //유저 db에 저장
        User user = saveOrUpdate(userProfileDto);

//        String accessToken = jwtTokenProvider.createAccessToken(String.valueOf(member.getId()));
//        String refreshToken = jwtTokenProvider.createRefreshToken();
//        return LoginResponse.builder()
//                .id(member.getId())
//                .name(member.getName())
//                .email(member.getEmail())
//                .imageUrl(member.getImageUrl())
//                .role(member.getRole())
//                .tokenType("Bearer")
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build();

        return null;
    }

    private User saveOrUpdate(UserProfileDto userProfileDto) {

//        User user = userRepository.findByEmail(userProfileDto.getEmail())
//                .map(entity -> entity.update(
//                        userProfileDto.getEmail(), userProfileDto.getImgUrl()))
//                .orElseGet(userProfileDto::toUser);
//        return userRepository.save(user);
        return null;
    }

    private UserProfileDto getUserProfile(String providerName, OauthTokenResponseDto tokenResponseDto, OauthProvider provider) {

        Map<String, Object> userAttributes = getUserAttributes(provider, tokenResponseDto);

        // TODO 유저 정보(map)를 통해 UserProfile 만들기
        return OauthAttributes.extract(providerName, userAttributes);
    }

    private Map<String, Object> getUserAttributes(OauthProvider provider, OauthTokenResponseDto tokenResponse) {
        return WebClient.create()
                .get()
                .uri(provider.getUserInfoUrl())
                .headers(header -> header.setBearerAuth(tokenResponse.getAccessToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();
    }
    private OauthTokenResponseDto getToken(String code, OauthProvider provider) {
        return WebClient.create()
                .post()
                .uri(provider.getTokenUrl())
                .headers(header -> {
                    header.setBasicAuth(provider.getClientId(), provider.getClientSecret());
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(tokenRequest(code, provider))
                .retrieve()
                .bodyToMono(OauthTokenResponseDto.class)
                .block();
    }

    private Object tokenRequest(String code, OauthProvider provider) {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", provider.getRedirectUrl());
        return formData;

    }
}
