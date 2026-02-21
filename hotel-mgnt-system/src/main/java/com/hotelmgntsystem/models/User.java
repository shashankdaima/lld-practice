package com.hotelmgntsystem.models;
import com.hotelmgntsystem.enums.AuthType;

public class User {
    String name;
    String email;
    String password;
    AuthType authType;

    public User(String name, String email, String password, AuthType authType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.authType = authType;
    }

    public User(String name, String email, AuthType authType) {
        this.name = name;
        this.email = email;
        this.authType = authType;
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
}
