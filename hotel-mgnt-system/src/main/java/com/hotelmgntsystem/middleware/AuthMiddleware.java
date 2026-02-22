package com.hotelmgntsystem.middleware;

import com.hotelmgntsystem.models.AuthContext;

/**
 * Contract for every handler in the auth middleware chain.
 *
 * Each handler decides:
 *   - pass: call next.handle(context) and return its result
 *   - block: throw an exception or return false without calling next
 *
 * The `next` parameter is the next handler in the chain.
 * If there is no next handler, calling next.handle() means the chain is complete.
 */
public interface AuthMiddleware {
    boolean handle(AuthContext context, AuthMiddleware next);
}
