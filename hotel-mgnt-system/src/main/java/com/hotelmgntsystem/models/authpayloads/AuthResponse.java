package com.hotelmgntsystem.models.authpayloads;

import com.hotelmgntsystem.enums.AuthStatus;

public class AuthResponse {
    private String token;
    private AuthStatus status;
    public AuthResponse(String token, AuthStatus status) {
        this.token = token;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public AuthStatus getStatus() {
        return status;
    }
}
