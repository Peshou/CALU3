package com.javelin.security.xauth;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Intel on 01.03.2016.
 */
public class XAuthTokenConfigurer  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {
    private TokenProvider tokenProvider;
    private UserDetailsService userDetailsService;

    public XAuthTokenConfigurer(UserDetailsService userDetailsService, TokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        XAuthTokenFilter customFilter = new XAuthTokenFilter(userDetailsService,tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
