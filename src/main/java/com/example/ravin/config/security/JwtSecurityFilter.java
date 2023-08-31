package com.example.ravin.config.security;

import com.example.ravin.utils.JwtUtils;
import com.example.ravin.domains.user.UserService;
import com.example.ravin.exceptions.JwtSecurityException;
import com.example.ravin.utils.constants.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtSecurityFilter extends OncePerRequestFilter {
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = JwtUtils.getTokenFromHeader(request).orElseThrow(() -> new JwtSecurityException(ErrorMessages.TOKEN_NOT_FOUND));
            String login = JwtUtils.validateToken(token);

            UserDetails user = userService.loadUserByUsername(login);

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));

        } catch (JwtSecurityException | EntityNotFoundException ex) {
            log.info("JwtSecurityFilter.doFilterInternal: " + ex.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
    }
}
