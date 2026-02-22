package com.hotelmgntsystem.middleware;

import com.hotelmgntsystem.enums.AuthType;
import com.hotelmgntsystem.models.AuthContext;
import com.hotelmgntsystem.models.User;

/**
 * Step 1: Check that the token is present and can be decoded into a User.
 *
 * In a real system this would verify the JWT signature and extract claims.
 * Here we stub it — if the token is non-null and non-empty, we treat it as valid
 * and populate context.user so downstream handlers can use it.
 */
public class TokenValidationHandler implements AuthMiddleware {

    @Override
    public boolean handle(AuthContext context, AuthMiddleware next) {
        String token = context.getToken();

        if (token == null || token.isBlank()) {
            System.out.println("[TokenValidationHandler] BLOCKED — token is missing");
            return false;
        }

        // Stub: in reality, decode JWT → extract user info from claims
        User user = decodeToken(token);
        if (user == null) {
            System.out.println("[TokenValidationHandler] BLOCKED — token is invalid");
            return false;
        }

        context.setUser(user);
        System.out.println("[TokenValidationHandler] PASSED — token is valid for " + user.getEmail());
        return next.handle(context, null); // pass to next handler
    }

    private User decodeToken(String token) {
        // Stub: pretend every non-empty token belongs to a GUEST user
        // Real impl: JWT.decode(token) → validate signature → map claims to User
        return new User("Test User", "test@test.com", AuthType.EMAIL);
    }
}
