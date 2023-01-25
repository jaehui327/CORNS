package com.w6w.corns.auth;

import com.w6w.corns.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@ConfigurationProperties(prefix = "oauth2")
public class OauthProperties {

    private final Map<String, User> user = new HashMap<>();
    private final Map<String, Provider> provider = new HashMap<>();

    @Getter
    @Setter
    public static class User{
        private String clientId;
        private String clientSecret;
        private String redirectUri;

    }

    @Getter
    @Setter
    public static class Provider{
        private String tokenUri;
        private String userInfoUri;
        private String userNameAttribute;

    }

}
