package com.javelin;

import com.javelin.domain.Authority;
import com.javelin.domain.User;
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
        if (userRepository.findOneByUsername("system") == null) {
            User system = new User("system", "$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG", "System", "System", "system@localhost", true);
            Set<Authority> authoritiesSystem = new HashSet<>();
            authoritiesSystem.add(authorityRepository.findOne("ROLE_USER"));
            authoritiesSystem.add(authorityRepository.findOne("ROLE_ADMIN"));
            system.setAuthorities(authoritiesSystem);
            userRepository.save(system);
        }

        if (userRepository.findOneByUsername("anonymoususer") == null) {
            User anonymousUser = new User("anonymoususer", "$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO", "Anonymouse", "User", "anonymouse@localhost", true);
            userRepository.save(anonymousUser);
        }
        if (userRepository.findOneByUsername("admin") == null) {
            User admin = new User("admin", "$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC", "Administrator", "Administrator", "admin@localhost", true);
            Set<Authority> authoritiesAdmin = new HashSet<>();
            authoritiesAdmin.add(authorityRepository.findOne("ROLE_USER"));
            authoritiesAdmin.add(authorityRepository.findOne("ROLE_ADMIN"));
            admin.setAuthorities(authoritiesAdmin);
            userRepository.save(admin);
        }
        if (userRepository.findOneByUsername("user") == null) {
            User user = new User("user", "$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K", "User", "User", "user@localhost", true);
            Set<Authority> authoritiesUser = new HashSet<>();
            authoritiesUser.add(authorityRepository.findOne("ROLE_USER"));
            user.setAuthorities(authoritiesUser);
            userRepository.save(user);
        }
    }
}
