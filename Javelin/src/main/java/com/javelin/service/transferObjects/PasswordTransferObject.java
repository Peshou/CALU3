package com.javelin.service.transferObjects;

public class PasswordTransferObject {
    private String oldPassword;
    private String newPassword;

    public PasswordTransferObject() {
    }

    public PasswordTransferObject(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    @Override
    public String toString() {
        return "PasswordTransferObject{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
