package com.hotelmgntsystem.strategy.auth;

import com.hotelmgntsystem.enums.AuthType;
import com.hotelmgntsystem.models.authpayloads.AuthPayload;

public interface AuthStrategy {
    boolean execute(AuthPayload authPayload);
}
