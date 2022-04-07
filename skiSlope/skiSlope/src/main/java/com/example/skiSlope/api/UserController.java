package com.example.skiSlope.api;

import com.example.skiSlope.model.User;
import com.example.skiSlope.model.response.UserDetailedInformationResponse;
import com.example.skiSlope.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private UserService userService;


    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    @GetMapping("/info")
    public UserDetailedInformationResponse getUserDetailedInformation(){
        String loggedUserUsername = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = userService.getUser(loggedUserUsername);

        return UserDetailedInformationResponse.builder()
                .username(loggedUser.getUsername())
                .firstName(loggedUser.getFirstName())
                .lastName(loggedUser.getLastName())
                .email(loggedUser.getEmail())
                .build();
    }

}

