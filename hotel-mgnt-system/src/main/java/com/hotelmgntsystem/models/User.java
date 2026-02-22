package com.hotelmgntsystem.models;

import com.hotelmgntsystem.enums.AuthType;
import com.hotelmgntsystem.enums.Role;

public class User {
    String name;
    String email;
    String password;
    AuthType authType;
    Role role;

    public User(String name, String email, String password, AuthType authType, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.authType = authType;
        this.role = role;
    }

    public User(String name, String email, String password, AuthType authType) {
        this(name, email, password, authType, Role.GUEST);
    }

    public User(String name, String email, AuthType authType) {
        this(name, email, null, authType, Role.GUEST);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
