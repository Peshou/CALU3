package com.javelin.web.dto;


import com.javelin.domain.User;

/**
 * Created by Stefan on 2/27/2016.
 */
public class ManagedUserDTO extends UserDTO {
    private Long id;

    public ManagedUserDTO() {
    }

    public ManagedUserDTO(User user) {
        super(user);
        this.id = user.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ManagedUserDTO{" +
                "id=" + id +
                "} "+super.toString();
    }
}
