package com.javelin.service.transferObjects;

import com.javelin.model.Authority;
import com.javelin.model.User;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserTransferObject {
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 100;

    @Pattern(regexp = "^[a-z0-9]*$")
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private String userDescription;

    private byte[] userImage;
    private boolean active;


    private Set<String> authorities;

    public UserTransferObject() {
    }
    public UserTransferObject(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.authorities = getAuthorityString(user.getAuthorities());
        this.userDescription=user.getUserDescription();
        this.userImage = user.getUserImage();
        this.active = user.isActive();
    }
    private Set<String> getAuthorityString(Set<Authority> userAuthorities){
        Set<String> authorities=  new HashSet<String>();
        for(Authority authority : userAuthorities){
            authorities.add(authority.getName());
        }
        return authorities;
    }


    public UserTransferObject(String username, String password, String firstName, String lastName, String email, Set<String> authorities, String userDescription, byte[] userImage, boolean active) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.authorities = authorities;
        this.userDescription=userDescription;
        this.userImage = userImage;
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public byte[] getUserImage() {
        return userImage;
    }
    public boolean isActive() {
        return active;
    }
    public String getUserDescription() {
        return userDescription;
    }
    @Override
    public String toString() {
        return "UserTransferObject{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userDescription='" + userDescription + '\'' +
                ", userImage=" + Arrays.toString(userImage) +
                ", authorities=" + authorities +
                '}';
    }
}
