package com.example.Magazin.online;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SecuritySession {

    boolean isLogged;
    Integer userId;

    public void markUserLogged() {

        isLogged = true;
    }

    public boolean isUserLogged() {

        return isLogged;
    }

    public boolean userNotLogged() {
        return isLogged = false;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
