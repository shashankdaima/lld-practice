package com.hotelmgntsystem;

import com.hotelmgntsystem.enums.AuthType;
import com.hotelmgntsystem.enums.Role;
import com.hotelmgntsystem.middleware.AuthMiddlewareChain;
import com.hotelmgntsystem.middleware.RoleCheckHandler;
import com.hotelmgntsystem.middleware.TokenExpiryHandler;
import com.hotelmgntsystem.middleware.TokenValidationHandler;
import com.hotelmgntsystem.models.AuthContext;
import com.hotelmgntsystem.models.authpayloads.AuthResponse;
import com.hotelmgntsystem.models.authpayloads.EmailPayload;
import com.hotelmgntsystem.services.AuthService;
import com.hotelmgntsystem.services.AuthStrategyFactory;
import com.hotelmgntsystem.strategy.auth.AuthStrategy;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // --- Step 1: Authentication (who are you?) ---
        AuthStrategy strategy = AuthStrategyFactory.get(AuthType.EMAIL);
        AuthResponse response = AuthService.executeAuth(strategy, new EmailPayload("test@test.com", "password"));
        System.out.println("Token: " + response.getToken());
        System.out.println();

        // --- Step 2: Authorization (are you allowed?) ---
        // Build the chain once — reuse for every protected action
        AuthMiddlewareChain chain = new AuthMiddlewareChain(List.of(
                new TokenValidationHandler(),
                new TokenExpiryHandler(),
                new RoleCheckHandler()
        ));

        // Simulate a GUEST trying to access a GUEST-only resource — should pass
        System.out.println("-- GUEST accessing GUEST resource --");
        AuthContext guestContext = new AuthContext(response.getToken(), Role.GUEST);
        boolean allowed = chain.execute(guestContext);
        System.out.println("Access granted: " + allowed);
        System.out.println();

        // Simulate a GUEST trying to access a STAFF resource — should be blocked
        System.out.println("-- GUEST accessing STAFF resource --");
        AuthContext staffContext = new AuthContext(response.getToken(), Role.STAFF);
        boolean blocked = chain.execute(staffContext);
        System.out.println("Access granted: " + blocked);
    }
}
