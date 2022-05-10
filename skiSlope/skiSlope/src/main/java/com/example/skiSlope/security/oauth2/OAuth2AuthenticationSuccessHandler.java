package com.example.skiSlope.security.oauth2;

import com.example.skiSlope.model.response.JwtTokensResponse;
import com.example.skiSlope.security.oauth2.utility.CookieUtils;
import com.example.skiSlope.security.utility.JwtGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.example.skiSlope.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String issuer = request.getRequestURL().toString();
        String accessToken = JwtGenerator.generateJWTAccessToken(authentication.getName(), authentication.getAuthorities() , issuer);
        String refreshToken = JwtGenerator.generateJWTRefreshToken(authentication.getName(), issuer);

        String targetUrl = getTargerUrl(request, accessToken, refreshToken);
        clearAuthenticationAttributes(request, response);

        if(getRedirectUriFromRequest(request).isPresent()){
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }else {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), new JwtTokensResponse(accessToken, refreshToken));
        }
    }

    private String getTargerUrl(HttpServletRequest request, String accessToken, String refreshToken) {

        Optional<String> redirectUri = getRedirectUriFromRequest(request);

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        return UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .build().toUriString();
}

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private Optional<String> getRedirectUriFromRequest(HttpServletRequest request){
        return CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);
    }
}
