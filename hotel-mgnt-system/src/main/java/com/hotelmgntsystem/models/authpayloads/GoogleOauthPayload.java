package com.hotelmgntsystem.models.authpayloads;

import com.hotelmgntsystem.enums.AuthType;

public class GoogleOauthPayload extends AuthPayload {
    private String googleToken;

    public GoogleOauthPayload(String googleToken) {
        this.googleToken = googleToken;
        super.type = AuthType.GOOGLE;
    }

    public String getGoogleToken() {
        return googleToken;
    }
}
