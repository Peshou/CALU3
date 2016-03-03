package com.javelin.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private org.springframework.security.core.userdetails.UserDetailsService userDetailsService;

    public AuthenticationProvider(org.springframework.security.core.userdetails.UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User does not exist");
        }
        String password = userDetails.getPassword();
        String tokenPassword = (String) token.getCredentials();
        if (!passwordEncoder.matches(tokenPassword, password)) {
            throw new BadCredentialsException("Invalid username/password");
        }
        userDetails.getAuthorities().size();
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
