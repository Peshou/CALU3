package com.javelin.web.rest;

import com.javelin.domain.Authority;
import com.javelin.domain.User;
import com.javelin.security.AuthoritiesConstants;
import com.javelin.service.AuthorityService;
import com.javelin.service.UserService;
import com.javelin.web.dto.ManagedUserDTO;
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
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;


    //update na veke postoechki korisnik od tabelata od administratorot
    @RequestMapping(value = "/users", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<?> updateUser(@RequestBody ManagedUserDTO managedUserDTO) throws URISyntaxException {
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
            return ResponseEntity.ok().body(new ManagedUserDTO(userService.findOneById(managedUserDTO.getId())));
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //vrakjanje na site korisnici za tabelata
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<List<ManagedUserDTO>> getAllUsers() throws URISyntaxException{
        List<User> users = userService.findAll();
        List<ManagedUserDTO> managedUserDTOs = new ArrayList<>();
        for(User user : users) {
            managedUserDTOs.add(new ManagedUserDTO(user));
        }
        return new ResponseEntity<>(managedUserDTOs, HttpStatus.OK);
    }

    //zemanje samo na eden korisnik
    @RequestMapping(value = "/users/{username:[_'.@a-z0-9-]+}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagedUserDTO> getUser(@PathVariable String username){
        ManagedUserDTO managedUserDTO = new ManagedUserDTO(userService.getUserWithAuthoritiesByLogin(username));
        if(managedUserDTO != null){
            return new ResponseEntity<>(managedUserDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //brishenje na korisnik (ne e poddzrzhano radi foreign key constraint) ?
    @RequestMapping(value = "/users/{username}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        userService.deleteUserInformation(username);
        return ResponseEntity.ok().build();
    }

}
