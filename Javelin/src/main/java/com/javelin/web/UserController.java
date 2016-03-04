package com.javelin.web;

import com.javelin.model.User;
import com.javelin.security.AuthoritiesConstants;
import com.javelin.service.AuthorityService;
import com.javelin.service.UserService;
import com.javelin.service.transferObjects.ManagedUserTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    //vrakjanje na site korisnici za tabelata
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public ResponseEntity<List<ManagedUserTransferObject>> getAllUsers() throws URISyntaxException {
        List<User> users = userService.findAll();
        List<ManagedUserTransferObject> managedUserDTOs = new ArrayList<>();
        for (User user : users) {
            managedUserDTOs.add(new ManagedUserTransferObject(user));
        }
        return new ResponseEntity<>(managedUserDTOs, HttpStatus.OK);
    }

    //brishenje na korisnik (ne e poddzrzhano radi foreign key constraint) ? trreba da se napravi cascade ama ke vidime otom potom
    @RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUserInformation(username);
        User user = userService.findOneByUsername(username);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok().build();
    }

}
