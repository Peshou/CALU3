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
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;
/*

    //update na veke postoechki korisnik od tabelata od administratorot
    @RequestMapping(value = "/users", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<?> updateUser(@RequestBody ManagedUserTransferObject managedUserDTO) throws URISyntaxException {
        User user = userService.findOneByEmail(managedUserDTO.getEmail());
        if (user != null && (!user.getId().equals(managedUserDTO.getId()))) {
            return new ResponseEntity<>("The e-mail address is already in use", HttpStatus.BAD_REQUEST);
        }
        user = userService.findOneByUsername(managedUserDTO.getUsername());
        if (user != null && (!user.getId().equals(managedUserDTO.getId()))) {
            return new ResponseEntity<>("The username is already in use", HttpStatus.BAD_REQUEST);
        }
        user = userService.findOneById(managedUserDTO.getId());
        if (user != null) {
            user.setUsername(managedUserDTO.getUsername());
            user.setFirstName(managedUserDTO.getFirstName());
            user.setLastName(managedUserDTO.getLastName());
            user.setEmail(managedUserDTO.getEmail());
            Set<Authority> authorities = user.getAuthorities(); // lazy init authorities
            authorities.clear();
            for (String authority : managedUserDTO.getAuthorities()) {
                authorities.add(authorityService.findOne(authority));
            }
            return ResponseEntity.ok().body(new ManagedUserTransferObject(userService.findOneById(managedUserDTO.getId())));
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    //vrakjanje na site korisnici za tabelata
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<List<ManagedUserTransferObject>> getAllUsers() throws URISyntaxException{
        List<User> users = userService.findAll();
        List<ManagedUserTransferObject> managedUserDTOs = new ArrayList<>();
        for(User user : users) {
            managedUserDTOs.add(new ManagedUserTransferObject(user));
        }
        return new ResponseEntity<>(managedUserDTOs, HttpStatus.OK);
    }

  /*  //zemanje samo na eden korisnik
    @RequestMapping(value = "/users/{username:[_'.@a-z0-9-]+}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagedUserTransferObject> getUser(@PathVariable String username){
        ManagedUserTransferObject managedUserDTO = new ManagedUserTransferObject(userService.getUserWithAuthoritiesByLogin(username));
        if(managedUserDTO != null){
            return new ResponseEntity<>(managedUserDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    //brishenje na korisnik (ne e poddzrzhano radi foreign key constraint) ? trreba da se napravi cascade ama ke vidime otom potom
    @RequestMapping(value = "/users/{username}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        userService.deleteUserInformation(username);
        return ResponseEntity.ok().build();
    }

}
