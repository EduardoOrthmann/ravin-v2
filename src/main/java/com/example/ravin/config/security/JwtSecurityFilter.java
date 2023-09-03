package com.example.ravin.config.security;

import com.example.ravin.domains.user.UserService;
import com.example.ravin.exceptions.JwtSecurityException;
import com.example.ravin.utils.JwtUtils;
import com.example.ravin.utils.constants.ErrorMessages;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final HandlerExceptionResolver resolver;

    public JwtSecurityFilter(UserService userService, JwtUtils jwtUtils, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (inWhiteList(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        try  {
            String token = jwtUtils.getTokenFromHeader(request).orElseThrow(() -> new JwtSecurityException(ErrorMessages.TOKEN_NOT_FOUND));
            String login = jwtUtils.validateToken(token);

            UserDetails user = userService.loadUserByUsername(login);

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));

            filterChain.doFilter(request, response);
        } catch (JwtSecurityException | UsernameNotFoundException e) {
            resolver.resolveException(request, response, null, e);
        }
    }

    private boolean inWhiteList(HttpServletRequest request) {
        boolean isWhiteListed = false;

        switch (request.getMethod()) {
            case "POST" -> isWhiteListed = SecurityFilter.POST_WHITELIST.contains(request.getRequestURI());
        }

        return isWhiteListed;
    }
}
