package com.hotelmgntsystem.models.authpayloads;

import com.hotelmgntsystem.enums.AuthType;

public class EmailPayload extends AuthPayload{
    private String email;
    private String password;
    public EmailPayload(String email, String password) {
        this.email = email;
        this.password = password;
        super.type = AuthType.EMAIL;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
