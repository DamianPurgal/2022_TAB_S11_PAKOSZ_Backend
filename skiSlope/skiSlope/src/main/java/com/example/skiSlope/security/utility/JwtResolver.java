package com.example.skiSlope.security.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import static com.example.skiSlope.security.ApplicationSecurityConfig.JWT_SECRET_KEY;

public class JwtResolver {

    public static DecodedJWT verifyJWTAndReturnDecodedJWT(String token) throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    public static String verifyJWTAndReturnUsername(String token) throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();

        return username;
    }

    public static String getUsernameFromDecodedJWT(DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }

}
