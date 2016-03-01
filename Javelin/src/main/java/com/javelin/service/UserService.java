package com.javelin.service;

import com.javelin.domain.User;
import com.javelin.web.dto.PasswordDTO;

import java.io.IOException;
import java.util.List;

/**
 * Created by Stefan on 2/26/2016.
 */

public interface UserService {
    User findOneByUsername(String username);

    User findOneByEmail(String email);

    User createUserInformation(String username, String password, String firstName, String lastName, String email, String description) throws IOException;

    User getUserWithAuthorities();

    void updateUserInformation(String firstName, String lastName, String email, String description);

    void updateUserImage(byte[] image);

    boolean changePassword(PasswordDTO passwordDTO);

    User findOneById(Long id);

    List<User> findAll();

    User getUserWithAuthoritiesByLogin(String username);

    void deleteUserInformation(String username);
}
