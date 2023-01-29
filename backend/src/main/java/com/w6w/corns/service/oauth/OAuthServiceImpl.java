package com.w6w.corns.service.oauth;

import com.w6w.corns.dto.oauth.GetSocialOauthRes;
import com.w6w.corns.util.Constant.SocialType;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService{

    private final GoogleOauth googleOauth;
    private final HttpServletResponse response;

    @Override
    public void request(SocialType socialType) throws IOException {

        //각 소셜 로그인 요청 시 소셜로그인 페이지로 리다이렉트
        String redirectURL;
        switch (socialType){
            case GOOGLE:
                redirectURL=googleOauth.getOathRedirectURL();
                System.out.println("redirectURL = " + redirectURL);
                break;
            default:
                throw new IllegalArgumentException();
        }
        response.sendRedirect(redirectURL);
    }

    @Override
    public GetSocialOauthRes oAuthLogin(SocialType socialType, String code) throws IOException {

        switch (socialType){
            case GOOGLE:
                ResponseEntity<String> accessTokenResponse = googleOauth.requestAccessToken(code);
                googleOauth.getAccessToken(accessTokenResponse);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return null;
    }
}
