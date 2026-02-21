package com.hotelmgntsystem.strategy.auth;

import com.hotelmgntsystem.constants.Constants;
import com.hotelmgntsystem.enums.AuthType;

import com.hotelmgntsystem.models.authpayloads.AuthPayload;
import com.hotelmgntsystem.models.authpayloads.GoogleOauthPayload;

public class GoogleOauthStrategy implements AuthStrategy {
    @Override
    public String execute(AuthPayload authPayload) {
        if (!(authPayload instanceof GoogleOauthPayload)) {
            throw new IllegalArgumentException("Invalid auth payload");
        }
        GoogleOauthPayload googleOauthPayload = (GoogleOauthPayload) authPayload;
        String googleToken = googleOauthPayload.getGoogleToken();
        // TODO: Add authentication logic using googleToken
        return Constants.AUTH_TOKEN;

    }
}
