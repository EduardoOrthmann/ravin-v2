package com.example.ravin.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ravin.domains.user.User;
import com.example.ravin.exceptions.JwtSecurityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Component
public class JwtUtils {
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";
    private static final String ZONE_OFFSET = "-03:00";
    private static final String JWT_CREATION_ERROR = "Erro ao gerar o token";

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
            throw new JwtSecurityException(JWT_CREATION_ERROR);
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
        return LocalDateTime.now().plusMinutes(Integer.parseInt(expiration)).toInstant(ZoneOffset.of(ZONE_OFFSET));
    }

    public Optional<String> getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            return Optional.empty();
        }

        return Optional.of(header.substring(7));
    }
}
