package com.example.skiSlope.service;

import com.example.skiSlope.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    List<User> getAllUsers();

    User getUser(String username);

}
