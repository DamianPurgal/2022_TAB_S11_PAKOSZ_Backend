package com.example.skiSlope.service;

import com.example.skiSlope.model.User;
import com.example.skiSlope.model.request.UserEditInformationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User addUser(User user);

    User getUser(String username);

    User getUser(Long userId);

    boolean isExistingUser(String username);

    void updateUserData(Long userId, UserEditInformationRequest userEditInformationRequest);

    void deleteUser(String username);

    void deleteUser(Long userId);

}
