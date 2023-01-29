package com.w6w.corns.dto.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GoogleOAuthToken {

    private String accessToken;
    private int expiresIn;
    private String scope;
    private String tokenType;
    private String idToken;
}
