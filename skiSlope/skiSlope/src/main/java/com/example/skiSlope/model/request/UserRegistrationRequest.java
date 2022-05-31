package com.example.skiSlope.model.request;

import com.example.skiSlope.model.User;
import com.example.skiSlope.model.enums.AuthenticationProvider;
import com.example.skiSlope.security.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class UserRegistrationRequest {

    @NotBlank
    @Size(min = 5, max = 20,
            message = "Username must be between 5 and 20 characters")
    private String username;

    @NotBlank
    @Size(min = 5, max = 20,
            message = "Password must be between 5 and 20 characters")
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "First name cannot be empty")
    private String lastName;

    public User userRegistrationRequestToUser(){
        return User.builder()
                .id(null)
                .username(username)
                .password(password)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .userRole(UserRole.CUSTOMER)
                .locked(false)
                .enabled(true)
                .authenticationProvider(AuthenticationProvider.LOCAL)
                .build();
    }
}
