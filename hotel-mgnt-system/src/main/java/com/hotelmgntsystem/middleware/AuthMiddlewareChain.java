package com.hotelmgntsystem.middleware;

import com.hotelmgntsystem.models.AuthContext;

import java.util.List;

/**
 * Assembles a list of handlers into a chain and executes them in order.
 *
 * Each handler receives the context and a `next` reference pointing to
 * the next handler in the list. The last handler receives a terminal
 * no-op as `next` so it can safely call next.handle() without null checks,
 * or simply return true on its own (as RoleCheckHandler does).
 *
 * Usage:
 *   AuthMiddlewareChain chain = new AuthMiddlewareChain(List.of(
 *       new TokenValidationHandler(),
 *       new TokenExpiryHandler(),
 *       new RoleCheckHandler()
 *   ));
 *   boolean allowed = chain.execute(context);
 */
public class AuthMiddlewareChain {

    private final List<AuthMiddleware> handlers;

    public AuthMiddlewareChain(List<AuthMiddleware> handlers) {
        this.handlers = handlers;
    }

    public boolean execute(AuthContext context) {
        if (handlers.isEmpty()) return true;
        return execute(context, 0);
    }

    private boolean execute(AuthContext context, int index) {
        if (index >= handlers.size()) return true; // end of chain

        AuthMiddleware current = handlers.get(index);

        // `next` is a lambda that advances the chain index
        AuthMiddleware next = (ctx, ignored) -> execute(ctx, index + 1);

        return current.handle(context, next);
    }
}
