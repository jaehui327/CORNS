package com.w6w.corns.auth;

import java.util.Arrays;
import java.util.Map;

public enum OauthAttributes {

    GOOGLE("google") {
        @Override
        public UserProfileDto of(Map<String, Object> attributes) {
            return UserProfileDto.builder()
                    .email((String) attributes.get("email"))
                    .imgUrl((String) attributes.get("picture"))
                    .build();
        }
    };

    private final String providerName;

    OauthAttributes(String name) {
        this.providerName = name;
    }

    public static UserProfileDto extract(String providerName, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> providerName.equals(provider.providerName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of(attributes);
    }

    public abstract UserProfileDto of(Map<String, Object> attributes);
}