package com.javelin.web.rest;

import com.javelin.domain.User;
import com.javelin.security.AuthoritiesConstants;
import com.javelin.security.SecurityUtils;
import com.javelin.service.UserService;
import com.javelin.web.dto.PasswordDTO;
import com.javelin.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by Stefan on 2/26/2016.
 */
@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> registerAccount(@Valid @RequestBody UserDTO userDTO, HttpServletRequest request) throws IOException {
        User user = userService.findOneByUsername(userDTO.getUsername());
        if (user != null) {
            return new ResponseEntity<>("username is already in use", HttpStatus.BAD_REQUEST);
        } else {
            user = userService.findOneByEmail(userDTO.getEmail());
            if (user != null) {
                return new ResponseEntity<>("e-mail address is already in use", HttpStatus.BAD_REQUEST);
            } else {
                userService.createUserInformation(userDTO.getUsername(), userDTO.getPassword(),
                        userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getUserDescription());
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String isAuthenticated(HttpServletRequest request) {
        return request.getRemoteUser();
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getAccount() {
        try {
            User user = userService.getUserWithAuthorities();
            if (user != null) {
                return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({AuthoritiesConstants.USER})
    public ResponseEntity<String> saveAccount(@RequestBody UserDTO userDTO) throws IOException {
        User existingUser = userService.findOneByEmail(userDTO.getEmail());
        if (existingUser != null && (!existingUser.getUsername().equalsIgnoreCase(userDTO.getUsername()))) {
            return new ResponseEntity<>("The email is already used", HttpStatus.BAD_REQUEST);
        }

        User user = userService.findOneByUsername(SecurityUtils.getCurrentUser().getUsername());
        try {
            userService.updateUserInformation(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getUserDescription());
            return new ResponseEntity<>(HttpStatus.OK);
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
    //  @Secured({AuthoritiesConstants.USER})
    public ResponseEntity<?> changePassword(@RequestBody PasswordDTO passwordDTO) {
        if (!checkPasswordLength(passwordDTO.getNewPassword())) {
            return new ResponseEntity<>("Incorrect password length", HttpStatus.BAD_REQUEST);
        }
        boolean success = userService.changePassword(passwordDTO);
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    private boolean checkPasswordLength(String password) {
        return (!StringUtils.isEmpty(password) &&
                password.length() >= UserDTO.PASSWORD_MIN_LENGTH &&
                password.length() <= UserDTO.PASSWORD_MAX_LENGTH);
    }

    @RequestMapping(value = "/photo2", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<InputStreamResource> testphoto() throws IOException {
        ClassPathResource pngImage = new ClassPathResource("static/defaultUserImg.png");

        return ResponseEntity.ok().contentLength(pngImage.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(pngImage.getInputStream()));

    }
}
