package com.javelin.web.dto;

/**
 * Created by Stefan on 2/29/2016.
 */
public class PasswordDTO {
    private String oldPassword;
    private String newPassword;

    public PasswordDTO() {
    }

    public PasswordDTO(String oldPassword, String newPassword) {
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
        return "PasswordDTO{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
