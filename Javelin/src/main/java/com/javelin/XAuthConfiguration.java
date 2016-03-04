package com.javelin;

import com.javelin.security.xauth.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XAuthConfiguration {
    @Bean
    public TokenProvider tokenProvider(){
        return new TokenProvider("javelinAuthSecret",1500);
    }
}
