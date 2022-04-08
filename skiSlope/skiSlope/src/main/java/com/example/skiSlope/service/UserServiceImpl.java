package com.example.skiSlope.service;

import com.example.skiSlope.exception.UserNotFoundException;
import com.example.skiSlope.exception.UserPasswordsDoesntMatchException;
import com.example.skiSlope.exception.UserUsernameIsNotAvailableException;
import com.example.skiSlope.model.User;
import com.example.skiSlope.model.request.UserEditInformationRequest;
import com.example.skiSlope.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    @Override
    public User addUser(User user) {
        if(isExistingUser(user.getUsername())){
            throw new UserUsernameIsNotAvailableException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean isExistingUser(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public void updateUserData(String username, UserEditInformationRequest userEditInformationRequest) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(userEditInformationRequest.getOldPassword(), user.getPassword())){
            throw new UserPasswordsDoesntMatchException();
        }

        user = userEditInformationRequest.updateUserInformation(user);
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
