package com.portfolio.ttbwebsite.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ApiKeyAuthenticationToken implements Authentication {
    private final String apiKey;
    private final boolean isAuthenticated;

    public ApiKeyAuthenticationToken(String apiKey, boolean isAuthenticated) {
        this.apiKey = apiKey;
        this.isAuthenticated = isAuthenticated;
    }

    public String getApiKey() {
        return apiKey;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
