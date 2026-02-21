package com.hotelmgntsystem.strategy.auth;

import com.hotelmgntsystem.enums.AuthType;
import com.hotelmgntsystem.models.authpayloads.AuthPayload;

public interface AuthStrategy {
    String execute(AuthPayload authPayload);
}
