package com.hotelmgntsystem.strategy.auth;

import com.hotelmgntsystem.enums.AuthType;

import com.hotelmgntsystem.models.authpayloads.AuthPayload;
import com.hotelmgntsystem.models.authpayloads.GoogleOauthPayload;

public class GoogleOauthStrategy implements AuthStrategy {
    @Override
    public boolean execute(AuthType authType, AuthPayload authPayload) {
        if (authType == null || authType != AuthType.GOOGLE) {
            throw new IllegalArgumentException("Invalid auth type");
        }
        if (!(authPayload instanceof GoogleOauthPayload)) {
            throw new IllegalArgumentException("Invalid auth payload");
        }
        return true;

    }
}
