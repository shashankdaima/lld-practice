package com.hotelmgntsystem.services;

import com.hotelmgntsystem.enums.AuthType;
import com.hotelmgntsystem.strategy.auth.AuthStrategy;
import com.hotelmgntsystem.strategy.auth.EmailPasswordAuthStrategy;
import com.hotelmgntsystem.strategy.auth.GoogleOauthStrategy;

public class AuthStrategyFactory {
    public static AuthStrategy get(AuthType authType) {
        switch (authType) {
            case EMAIL:
                return new EmailPasswordAuthStrategy();
            case GOOGLE:
                return new GoogleOauthStrategy();
            default:
                throw new IllegalArgumentException("Invalid auth type");
        }
    }
}
