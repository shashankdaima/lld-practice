package com.hotelmgntsystem.models;

import com.hotelmgntsystem.enums.Role;

/**
 * Carries all information a middleware handler needs to make a decision.
 * - token       : the raw JWT string from the request
 * - requiredRole: the minimum role the caller must have to proceed
 * - user        : filled in by TokenValidationHandler once the token is decoded;
 *                 null until that point
 */
public class AuthContext {
    private final String token;
    private final Role requiredRole;
    private User user; // populated after token is validated

    public AuthContext(String token, Role requiredRole) {
        this.token = token;
        this.requiredRole = requiredRole;
    }

    public String getToken() {
        return token;
    }

    public Role getRequiredRole() {
        return requiredRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
