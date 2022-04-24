package com.example.skiSlope.api;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.skiSlope.exception.JwtExpiredException;
import com.example.skiSlope.exception.JwtValidationException;
import com.example.skiSlope.exception.UserUsernameIsNotAvailableException;
import com.example.skiSlope.model.request.UserRegistrationRequest;
import com.example.skiSlope.model.response.JwtTokensResponse;
import com.example.skiSlope.model.User;
import com.example.skiSlope.security.UserRole;
import com.example.skiSlope.security.utility.JwtGenerator;
import com.example.skiSlope.security.utility.JwtResolver;
import com.example.skiSlope.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.security.Principal;

import static com.example.skiSlope.security.ApplicationSecurityConfig.GOOGLE_ACCOUNT_USERNAME_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@AllArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    private UserService userService;

    @PreAuthorize("permitAll()")
    @GetMapping("/refreshToken")
    public JwtTokensResponse refreshJWT(HttpServletRequest request, HttpServletResponse response){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                String username = JwtResolver.verifyJWTAndReturnUsername(refreshToken);
                User user = userService.getUser(username);

                String accessToken = JwtGenerator.generateJWTAccessToken(user, request.getRequestURL().toString());
                return new JwtTokensResponse(accessToken, refreshToken);
            } catch(TokenExpiredException exception){
                throw new JwtExpiredException();

            } catch(JWTVerificationException exception){
                throw new JwtValidationException();
            }
        }else{
            throw new JwtValidationException();
        }
    }

    @PostMapping("/register")
    public void registerUser(@Valid @NonNull @RequestBody UserRegistrationRequest userRegistrationRequest){
        User user = userRegistrationRequest.userRegistrationRequestToUser();
        user.setUserRole(UserRole.CUSTOMER);
        if(user.getUsername().startsWith(GOOGLE_ACCOUNT_USERNAME_PREFIX))
            throw new UserUsernameIsNotAvailableException();
        userService.addUser(user);
    }

}

