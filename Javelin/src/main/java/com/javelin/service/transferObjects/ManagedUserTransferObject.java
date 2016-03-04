package com.javelin.service.transferObjects;


import com.javelin.model.User;

public class ManagedUserTransferObject extends UserTransferObject {
    private Long id;

    public ManagedUserTransferObject() {
    }

    public ManagedUserTransferObject(User user) {
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
        return "ManagedUserTransferObject{" +
                "id=" + id +
                "} "+super.toString();
    }
}
