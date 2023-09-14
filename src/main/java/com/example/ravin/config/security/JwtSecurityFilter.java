package com.example.ravin.config.security;

import com.example.ravin.domains.user.UserService;
import com.example.ravin.exceptions.JwtSecurityException;
import com.example.ravin.utils.JwtUtils;
import com.example.ravin.utils.constants.ErrorMessages;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@NonNullApi
@RequiredArgsConstructor
public class JwtSecurityFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = jwtUtils.getTokenFromHeader(request).orElseThrow(() -> new JwtSecurityException(ErrorMessages.TOKEN_NOT_FOUND));
            String login = jwtUtils.validateToken(token);

            UserDetails user = userService.loadUserByUsername(login);

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));

            filterChain.doFilter(request, response);
        } catch (JwtSecurityException | UsernameNotFoundException e) {
            resolver.resolveException(request, response, null, e);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return SecurityFilter.WHITELIST.entrySet().stream()
                .anyMatch(entry -> entry.getKey().equals(HttpMethod.valueOf(request.getMethod())) &&
                        entry.getValue().stream().anyMatch(uri -> uri.equals(request.getRequestURI())));
    }
}
