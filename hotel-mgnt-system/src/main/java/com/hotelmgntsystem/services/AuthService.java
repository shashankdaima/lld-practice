package com.hotelmgntsystem.services;

import com.hotelmgntsystem.enums.AuthStatus;
import com.hotelmgntsystem.enums.AuthType;
import com.hotelmgntsystem.exceptions.UnimplmentedException;
import com.hotelmgntsystem.models.authpayloads.AuthPayload;
import com.hotelmgntsystem.models.authpayloads.AuthResponse;
import com.hotelmgntsystem.strategy.auth.AuthStrategy;

public class AuthService {
    // Use the AuthStrategyFactory to get the appropriate strategy
    public static AuthResponse executeAuth(AuthStrategy strategy, AuthPayload authPayload) {
        try {
            String token = strategy.execute(authPayload);
            return new AuthResponse(token, AuthStatus.AUTH);
        } catch (Exception e) {
            return new AuthResponse(null, AuthStatus.SYSTEM_ERROR);
        }
    }

}
