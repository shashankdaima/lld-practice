package com.hotelmgntsystem.services;

import com.hotelmgntsystem.enums.AuthType;
import com.hotelmgntsystem.exceptions.UnimplmentedException;
import com.hotelmgntsystem.models.authpayloads.AuthPayload;
import com.hotelmgntsystem.strategy.auth.AuthStrategy;

public class AuthService {
    
    public static boolean executeAuth(AuthStrategy strategy, AuthPayload authPayload) {
        return strategy.execute(authPayload);
    }

}
