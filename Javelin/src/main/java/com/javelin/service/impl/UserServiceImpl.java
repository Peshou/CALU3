package com.javelin.service.impl;

import com.javelin.domain.Authority;
import com.javelin.domain.User;
import com.javelin.repository.AuthorityRepository;
import com.javelin.repository.UserRepository;
import com.javelin.security.SecurityUtils;
import com.javelin.service.UserService;
import com.javelin.web.dto.PasswordDTO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Stefan on 2/26/2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public User findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User createUserInformation(String username, String password, String firstName, String lastName, String email, String description) throws IOException {
        User newUser = new User();
        Authority authority = authorityRepository.findOne("ROLE_USER");
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setUsername(username);
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setUserDescription(description);
        File img = ResourceUtils.getFile("classpath:static/defaultUserImg.png");
        byte[] imag = IOUtils.toByteArray(new FileInputStream(img));
        newUser.setUserImage(imag);
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        newUser.setActive(true);
        userRepository.save(newUser);
        return newUser;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserWithAuthorities() {
        User user = userRepository.findOneByUsername(SecurityUtils.getCurrentUser().getUsername());
        if (user != null) {
            user.getAuthorities().size();//lazy init Authority class
        }
        return user;
    }

    @Override
    public void updateUserInformation(String firstName, String lastName, String email, String description, boolean active) {
        User user = userRepository.findOneByUsername(SecurityUtils.getCurrentUser().getUsername());
        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setUserDescription(description);
            user.setActive(active);
            //    user.setUserImage(image);
            userRepository.save(user);
        }
    }

    @Override
    public void updateUserImage(byte[] image) {
        User user = userRepository.findOneByUsername(SecurityUtils.getCurrentUser().getUsername());
        if (user != null) {
            user.setUserImage(image);
            userRepository.save(user);
        }
    }

    @Override
    public boolean changePassword(PasswordDTO passwordDTO) {
        User user = userRepository.findOneByUsername(SecurityUtils.getCurrentUser().getUsername());
        if (user != null) {
            String encryptedOldPassword = passwordEncoder.encode(passwordDTO.getOldPassword());
            boolean matches = passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword());
            if (matches) {
                String encryptedPassword = passwordEncoder.encode(passwordDTO.getNewPassword());
                user.setPassword(encryptedPassword);
                userRepository.save(user);
                return true;
            }else{
                return false;
            }

        }
        return false;
    }

    @Override
    public User findOneById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserWithAuthoritiesByLogin(String username) {
        User user = userRepository.findOneByUsername(username);
        if (user != null) {
            user.getAuthorities().size();
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteUserInformation(String username) {
        User user = userRepository.findOneByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
