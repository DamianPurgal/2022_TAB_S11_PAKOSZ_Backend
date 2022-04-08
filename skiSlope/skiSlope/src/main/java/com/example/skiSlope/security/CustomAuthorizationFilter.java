package com.example.skiSlope.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.skiSlope.api.handlers.DTO.ErrorResponse;
import com.example.skiSlope.security.utility.JwtResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.skiSlope.security.ApplicationSecurityConfig.LOGIN_URL;
import static com.example.skiSlope.security.ApplicationSecurityConfig.REFRESH_URL;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(isRequestURLAvailableForNotLoggedInUsers(request)){
            filterChain.doFilter(request, response);
        } else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);

            if(isAuthorizationHeaderCorrect(authorizationHeader)){
                try{
                    String token = authorizationHeader.substring("Bearer ".length());

                    UsernamePasswordAuthenticationToken authenticationToken = JwtResolver.verifyJWTAndReturnAuthenticationToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);

                }catch(TokenExpiredException exception){
                    addErrorMessageToResponse(response, HttpStatus.FORBIDDEN.value(), "JWT expired");

                }catch(JWTVerificationException exception){
                    addErrorMessageToResponse(response, HttpStatus.FORBIDDEN.value(), "JWT validation error");

                }
                catch(Exception exception){
                    addErrorMessageToResponse(response, HttpStatus.FORBIDDEN.value(), "Authorization denied. Error occured.");
                }
            }else{
                filterChain.doFilter(request, response);
            }
        }
    }

    private void addErrorMessageToResponse(HttpServletResponse response, int errorCode, String errorMessage) throws IOException{
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), new ErrorResponse(errorCode, errorMessage));
    }

    private boolean isRequestURLAvailableForNotLoggedInUsers(HttpServletRequest request){
        return request.getServletPath().equals(LOGIN_URL) || request.getServletPath().equals(REFRESH_URL);
    }

    private boolean isAuthorizationHeaderCorrect(String authorizationHeader){
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }
}
