package com.javelin.web;

import com.javelin.model.User;
import com.javelin.security.AuthoritiesConstants;
import com.javelin.security.SecurityUtils;
import com.javelin.service.UserService;
import com.javelin.service.transferObjects.PasswordTransferObject;
import com.javelin.service.transferObjects.UserTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> registerAccount(@Valid @RequestBody UserTransferObject userTransferObject, HttpServletRequest request) throws IOException {
        User user = userService.findOneByUsername(userTransferObject.getUsername());
        if (user != null) {
            return new ResponseEntity<>("username is already in use", HttpStatus.BAD_REQUEST);
        } else {
            user = userService.findOneByEmail(userTransferObject.getEmail());
            if (user != null) {
                return new ResponseEntity<>("e-mail address is already in use", HttpStatus.BAD_REQUEST);
            } else {
                userService.createUserInformation(userTransferObject.getUsername(), userTransferObject.getPassword(),
                        userTransferObject.getFirstName(), userTransferObject.getLastName(), userTransferObject.getEmail(), userTransferObject.getUserDescription());
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserTransferObject> getAccount() {
        try {
            User user = userService.getUserWithAuthorities();
            if (user != null) {
                return new ResponseEntity<>(new UserTransferObject(user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({AuthoritiesConstants.USER})
    public ResponseEntity<String> saveAccount(@RequestBody UserTransferObject userTransferObject) throws IOException {
        User existingUser = userService.findOneByEmail(userTransferObject.getEmail());
        if (existingUser != null && (!existingUser.getUsername().equalsIgnoreCase(userTransferObject.getUsername()))) {
            return new ResponseEntity<>("The email is already used", HttpStatus.BAD_REQUEST);
        }

        User user = userService.findOneByUsername(SecurityUtils.getCurrentUser().getUsername());
        try {
            userService.updateUserInformation(userTransferObject.getFirstName(), userTransferObject.getLastName(), userTransferObject.getEmail(), userTransferObject.getUserDescription(), userTransferObject.isActive());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/account/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes;
        if (!file.isEmpty()) {
            bytes = file.getBytes();
            userService.updateUserImage(bytes);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/account/change_password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({AuthoritiesConstants.USER})
    public ResponseEntity<?> changePassword(@RequestBody PasswordTransferObject passwordTransferObject) {
        boolean success = userService.changePassword(passwordTransferObject);
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }


}
