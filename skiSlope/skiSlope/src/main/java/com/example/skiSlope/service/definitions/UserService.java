package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.User;
import com.example.skiSlope.model.request.UserEditInformationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User addUser(User user);

    User getUser(String username);

    User getUser(Long userId);

    boolean isExistingUser(String username);

    void updateUserData(Long userId, UserEditInformationRequest userEditInformationRequest);

    void deleteUser(String username);

    void deleteUser(Long userId);

    List<User> getAllUsers();

}
