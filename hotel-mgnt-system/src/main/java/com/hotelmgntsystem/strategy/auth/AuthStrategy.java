package com.hotelmgntsystem.strategy.auth;

public interface AuthStrategy {
    /**
     * Execute the authentication strategy.
     * 
     * @param username The username for authentication (required)
     * @param password The password for authentication (optional, can be null)
     */
    void execute(String username, String password);
}
