package com.portfolio.ttbwebsite.security.authentication;

import com.portfolio.ttbwebsite.security.Security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    public ApiKeyAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader("X-API-KEY");

        if (apiKey == null){
            this.logger.trace("Did not Find API key in request");
            return;
        }

        ApiKeyAuthenticationToken authentication = new ApiKeyAuthenticationToken(apiKey,false);
        Authentication result = this.authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(result);
        filterChain.doFilter(request,response);


    }
}
