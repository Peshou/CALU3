package com.javelin;

import com.javelin.model.Authority;
import com.javelin.model.User;
import com.javelin.repository.AuthorityRepository;
import com.javelin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
public class JavelinApplication implements CommandLineRunner {
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(JavelinApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        authorityRepository.save(new Authority("ROLE_USER"));
        authorityRepository.save(new Authority("ROLE_ADMIN"));
        if (userRepository.findOneByUsername("admin") == null) {
            User admin = new User("admin", "$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC", "Administrator", "Administrator", "admin@localhost", true);
            Set<Authority> authoritiesAdmin = new HashSet<>();
            authoritiesAdmin.add(authorityRepository.findOne("ROLE_USER"));
            authoritiesAdmin.add(authorityRepository.findOne("ROLE_ADMIN"));
            admin.setAuthorities(authoritiesAdmin);
            userRepository.save(admin);
        }
    }
}
