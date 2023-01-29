package com.w6w.corns.service.oauth;

import com.w6w.corns.dto.oauth.GetSocialOauthRes;
import com.w6w.corns.util.Constant;
import java.io.IOException;

public interface OAuthService {

    void request(Constant.SocialType socialType) throws IOException;
    GetSocialOauthRes oAuthLogin(Constant.SocialType socialType, String code) throws IOException;
}
