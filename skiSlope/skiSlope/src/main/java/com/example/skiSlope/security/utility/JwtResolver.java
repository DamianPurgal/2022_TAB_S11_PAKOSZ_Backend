package com.example.skiSlope.security.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

import static com.example.skiSlope.security.ApplicationSecurityConfig.JWT_SECRET_KEY;
import static java.util.Arrays.stream;

public class JwtResolver {

    public static UsernamePasswordAuthenticationToken verifyJWTAndReturnAuthenticationToken(String token) throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role))
        );
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    public static String verifyJWTAndReturnUsername(String token) throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();

        return username;
    }
}