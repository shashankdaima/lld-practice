package com.hotelmgntsystem;

import com.hotelmgntsystem.enums.AuthType;
import com.hotelmgntsystem.models.authpayloads.AuthResponse;
import com.hotelmgntsystem.models.authpayloads.EmailPayload;
import com.hotelmgntsystem.services.AuthService;
import com.hotelmgntsystem.services.AuthStrategyFactory;
import com.hotelmgntsystem.strategy.auth.AuthStrategy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from hotel-mgnt-system!");
        AuthStrategy strategy = AuthStrategyFactory.get(AuthType.EMAIL);
        AuthResponse response=AuthService.executeAuth(strategy, new EmailPayload("test@test.com", "password"));
        System.out.println(response.getToken());
    }
}
