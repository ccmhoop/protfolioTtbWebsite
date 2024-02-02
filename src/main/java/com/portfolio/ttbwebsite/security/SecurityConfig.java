package com.portfolio.ttbwebsite.security;

import com.portfolio.ttbwebsite.security.authentication.ApiKeyAuthenticationFilter;
import com.portfolio.ttbwebsite.security.authentication.ApiKeyAuthenticationProvider;
import com.portfolio.ttbwebsite.security.authentication.BasicAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(customizer -> {
            customizer.anyRequest().authenticated();
        })
                .httpBasic(Customizer.withDefaults());
        http.addFilterBefore(new ApiKeyAuthenticationFilter(authenticationManager()), LogoutFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        BasicAuthenticationProvider basicAuthenticationProvider = new BasicAuthenticationProvider();
        ApiKeyAuthenticationProvider apiKeyAuthenticationProvider = new ApiKeyAuthenticationProvider();
        return new ProviderManager(basicAuthenticationProvider, apiKeyAuthenticationProvider);
    }

}
