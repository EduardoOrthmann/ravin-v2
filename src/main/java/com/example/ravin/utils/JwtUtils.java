package com.example.ravin.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ravin.domains.user.User;
import com.example.ravin.exceptions.JwtSecurityException;
import com.example.ravin.utils.constants.Constants;
import com.example.ravin.utils.constants.ErrorMessages;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@UtilityClass
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration}")
    private String expiration;

    public String generateToken(User user) {
        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getLogin())
                    .withClaim("id", String.valueOf(user.getId()))
                    .withExpiresAt(getExpirationTime())
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException ex) {
            throw new JwtSecurityException(ErrorMessages.JWT_CREATION_ERROR);
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            throw new JwtSecurityException();
        }
    }

    private Instant getExpirationTime() {
        return LocalDateTime.now().plusMinutes(Integer.parseInt(expiration)).toInstant(ZoneOffset.of(Constants.ZONE_OFFSET));
    }

    public Optional<String> getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader(Constants.HEADER_STRING);

        if (header == null || !header.startsWith(Constants.TOKEN_PREFIX)) {
            return Optional.empty();
        }

        return Optional.of(header.substring(7));
    }
}
