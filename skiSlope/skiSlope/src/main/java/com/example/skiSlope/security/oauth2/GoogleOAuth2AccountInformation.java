package com.example.skiSlope.security.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class GoogleOAuth2AccountInformation {

    private Map<String, Object> attributes;

    public String getFirstName() {
        return (String) attributes.get("given_name");
    }

    public String getLastName() {
        return (String) attributes.get("family_name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

}
