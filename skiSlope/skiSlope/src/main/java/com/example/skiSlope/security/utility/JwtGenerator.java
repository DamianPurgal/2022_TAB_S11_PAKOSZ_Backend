package com.example.skiSlope.security.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.skiSlope.model.ScannerQR;
import com.example.skiSlope.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.skiSlope.security.ApplicationSecurityConfig.*;

public class JwtGenerator {

    public static String generateJWTAccessToken(User user, String issuer){
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY))
                .withIssuer(issuer)
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public static String generateJWTRefreshToken(User user, String issuer){
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_VALIDITY))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public static String generateJWTAccessToken(String username, Collection<? extends GrantedAuthority> authorities, String issuer){
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY))
                .withIssuer(issuer)
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public static String generateJWTRefreshToken(String username, String issuer){
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_VALIDITY))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public static String generateJWTAccessTokenForScanner(ScannerQR scanner, String issuer){

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_SCANNER"));
        return JWT.create()
                .withSubject(scanner.getLogin())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_SCANNER_ACCESS_TOKEN_VALIDITY))
                .withIssuer(issuer)
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public static String generateJWTRefreshTokenForScanner(ScannerQR scanner, String issuer){

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        return JWT.create()
                .withSubject(scanner.getLogin())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_SCANNER_REFRESH_TOKEN_VALIDITY))
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
