package com.example.skiSlope.security.oauth2;


import com.example.skiSlope.exception.UserNotFoundException;
import com.example.skiSlope.model.User;
import com.example.skiSlope.security.UserRole;
import com.example.skiSlope.service.definitions.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import static com.example.skiSlope.security.ApplicationSecurityConfig.GOOGLE_ACCOUNT_USERNAME_PREFIX;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2User oAuth2User) {
        GoogleOAuth2AccountInformation user = new GoogleOAuth2AccountInformation(oAuth2User.getAttributes());

        User loggedUser;
        try{
            loggedUser = userService.getUser(getGoogleUserUsername(user));
        }catch(UserNotFoundException exception){
            loggedUser = registerGoogleUser(user);
        }

        saveUserToSecurityContextHolder(loggedUser);

        return new GoogleOAuth2User(loggedUser.getUsername(), loggedUser.getAuthorities(), oAuth2User.getAttributes());
    }

    private String getGoogleUserUsername(GoogleOAuth2AccountInformation googleUser){

        String username = (String)googleUser.getAttributes().get("email");
        username = GOOGLE_ACCOUNT_USERNAME_PREFIX + username;

        return username;
    }

    private User registerGoogleUser(GoogleOAuth2AccountInformation googleUser){
        String username = getGoogleUserUsername(googleUser);

        User user = User.builder()
                .id(null)
                .username(username)
                .password(googleUser.getEmail())
                .email(googleUser.getEmail())
                .firstName(googleUser.getFirstName())
                .lastName(googleUser.getLastName())
                .userRole(UserRole.CUSTOMER)
                .locked(false)
                .enabled(true)
                .build();

        return userService.addUser(user);
    }

    private void saveUserToSecurityContextHolder(User user){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }


}