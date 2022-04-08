package com.example.skiSlope.model.request;

import com.example.skiSlope.model.User;
import com.example.skiSlope.security.UserRole;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class UserEditInformationRequest {

    @NotBlank
    @Size(min = 5, max = 20,
            message = "Password must be between 5 and 20 characters")
    private String oldPassword;

    @NotBlank
    @Size(min = 5, max = 20,
            message = "Password must be between 5 and 20 characters")
    private String newPassword;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "First name cannot be empty")
    private String lastName;

    public User updateUserInformation(User userToUpdate){
        return User.builder()
                .id(userToUpdate.getId())
                .username(userToUpdate.getUsername())
                .password(newPassword)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .userRole(userToUpdate.getUserRole())
                .enabled(userToUpdate.getEnabled())
                .locked(userToUpdate.getLocked())
                .build();
    }
}
