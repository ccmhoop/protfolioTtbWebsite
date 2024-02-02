package com.portfolio.ttbwebsite.security.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class BasicAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
       String username = token.getName();
       String password = token.getCredentials().toString();
       if ("test".equals(username) && "test".equals(password)){
           return new UsernamePasswordAuthenticationToken(username, password, null);
       }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
