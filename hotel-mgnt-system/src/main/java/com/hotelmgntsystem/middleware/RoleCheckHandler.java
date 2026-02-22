package com.hotelmgntsystem.middleware;

import com.hotelmgntsystem.enums.Role;
import com.hotelmgntsystem.models.AuthContext;
import com.hotelmgntsystem.models.User;

/**
 * Step 3: Check that the authenticated user's role meets the required role.
 *
 * Role hierarchy: ADMIN > STAFF > GUEST
 * If the required role is STAFF, both STAFF and ADMIN are allowed through.
 */
public class RoleCheckHandler implements AuthMiddleware {

    @Override
    public boolean handle(AuthContext context, AuthMiddleware next) {
        User user = context.getUser();
        Role required = context.getRequiredRole();
        Role userRole = user.getRole();

        if (!hasPermission(userRole, required)) {
            System.out.println("[RoleCheckHandler] BLOCKED — role " + userRole + " cannot access " + required + " resource");
            return false;
        }

        System.out.println("[RoleCheckHandler] PASSED — role " + userRole + " is allowed");

        // Last handler in the chain — no next to call, just return true
        return true;
    }

    private boolean hasPermission(Role userRole, Role requiredRole) {
        // ADMIN can do everything, STAFF can do STAFF+GUEST, GUEST can only do GUEST
        return userRole.ordinal() >= requiredRole.ordinal();
    }
}
