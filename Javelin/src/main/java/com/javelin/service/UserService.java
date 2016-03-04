package com.javelin.service;

import com.javelin.model.User;
import com.javelin.service.transferObjects.PasswordTransferObject;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User findOneByUsername(String username);

    User findOneByEmail(String email);

    User createUserInformation(String username, String password, String firstName, String lastName, String email, String description) throws IOException;

    User getUserWithAuthorities();

    void updateUserInformation(String firstName, String lastName, String email, String description,boolean active);

    void updateUserImage(byte[] image);

    boolean changePassword(PasswordTransferObject passwordTransferObject);


    List<User> findAll();


    void deleteUserInformation(String username);
}
