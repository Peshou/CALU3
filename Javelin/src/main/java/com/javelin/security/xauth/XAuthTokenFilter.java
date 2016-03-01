package com.javelin.security.xauth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Intel on 01.03.2016.
 */
public class XAuthTokenFilter extends GenericFilterBean {

    private final static String XAUTH_TOKEN_HEADER_NAME = "x-auth-token";
    private UserDetailsService userDetailsService;
    private TokenProvider tokenProvider;

    public XAuthTokenFilter(UserDetailsService userDetailsService, TokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String authToken = httpServletRequest.getHeader(XAUTH_TOKEN_HEADER_NAME);
            if (StringUtils.hasText(authToken)) {
                String username = this.tokenProvider.getUserNameFromToken(authToken);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (this.tokenProvider.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
            chain.doFilter(request, response);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}