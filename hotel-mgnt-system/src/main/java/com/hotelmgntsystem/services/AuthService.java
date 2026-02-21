package com.hotelmgntsystem.services;

import com.hotelmgntsystem.enums.AuthType;
import com.hotelmgntsystem.exceptions.UnimplmentedException;
import com.hotelmgntsystem.models.authpayloads.AuthPayload;

public class AuthService {
    
    public static boolean executeAuth(AuthType authType, AuthPayload authPayload){
        switch (authType) {
            case EMAIL:
                return new EmailPasswordAuthStrategy().execute(authType, authPayload);
            case GOOGLE:
                return new GoogleOauthStrategy().execute(authType, authPayload);
            default:
                throw new IllegalArgumentException("Invalid auth type");
        }
    }
}
