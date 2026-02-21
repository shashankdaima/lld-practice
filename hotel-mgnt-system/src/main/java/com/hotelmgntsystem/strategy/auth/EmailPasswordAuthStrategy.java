package com.hotelmgntsystem.strategy.auth;

import com.hotelmgntsystem.enums.AuthType;
import com.hotelmgntsystem.models.authpayloads.AuthPayload;
import com.hotelmgntsystem.models.authpayloads.EmailPayload;

public class EmailPasswordAuthStrategy implements AuthStrategy{

    @Override
    public boolean execute(AuthPayload authPayload) {
        if (!(authPayload instanceof EmailPayload)) {
            throw new IllegalArgumentException("Invalid auth payload");
        }
        return true;
    }
    
}
