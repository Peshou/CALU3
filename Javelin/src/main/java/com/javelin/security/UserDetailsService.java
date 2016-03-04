package com.javelin.security;

import com.javelin.model.Authority;
import com.javelin.model.User;
import com.javelin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lowerCaseUsername = username.toLowerCase();
        User userFromDatabase = userRepository.findOneByUsername(lowerCaseUsername);
        if (userFromDatabase != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Authority authority : userFromDatabase.getAuthorities()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
            }
            return new org.springframework.security.core.userdetails.User(lowerCaseUsername, userFromDatabase.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User " + lowerCaseUsername + " was not found in the database");
        }
    }
}

