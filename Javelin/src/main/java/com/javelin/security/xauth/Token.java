package com.javelin.security.xauth;

/**
 * Created by Intel on 01.03.2016.
 */
public class Token {
    private String token;
    private long expires;

    public Token(String token, long expires) {
        this.token = token;
        this.expires = expires;
    }

    public long getExpires() {
        return expires;
    }

    public String getToken() {
        return token;
    }
}
