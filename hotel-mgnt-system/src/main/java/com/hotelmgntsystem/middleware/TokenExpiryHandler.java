package com.hotelmgntsystem.middleware;

import com.hotelmgntsystem.models.AuthContext;

/**
 * Step 2: Check that the token has not expired.
 *
 * Runs after TokenValidationHandler, so context.getUser() is already populated.
 * In a real system, the JWT payload contains an `exp` claim (Unix timestamp).
 * We compare it against the current time and block if expired.
 */
public class TokenExpiryHandler implements AuthMiddleware {

    @Override
    public boolean handle(AuthContext context, AuthMiddleware next) {
        // Stub: in reality, extract `exp` from JWT claims and compare to Instant.now()
        boolean isExpired = isTokenExpired(context.getToken());

        if (isExpired) {
            System.out.println("[TokenExpiryHandler] BLOCKED — token has expired");
            return false;
        }

        System.out.println("[TokenExpiryHandler] PASSED — token is not expired");
        return next.handle(context, null);
    }

    private boolean isTokenExpired(String token) {
        // Stub: always return false (not expired) for now
        // Real impl: Jwts.parser().parseClaimsJws(token).getBody().getExpiration()
        return false;
    }
}
