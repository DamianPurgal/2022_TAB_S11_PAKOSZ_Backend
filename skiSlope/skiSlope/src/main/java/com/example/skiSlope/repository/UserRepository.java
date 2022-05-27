package com.example.skiSlope.repository;

import com.example.skiSlope.model.User;
import com.example.skiSlope.model.enums.AuthenticationProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndAuthenticationProvider(String username, AuthenticationProvider authenticationProvider);

    boolean existsUserByUsername(String username);

    void deleteByUsername(String username);

}
