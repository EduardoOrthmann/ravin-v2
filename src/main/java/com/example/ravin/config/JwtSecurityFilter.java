package com.example.ravin.config;

import com.example.ravin.utils.JwtUtils;
import com.example.ravin.domains.user.UserService;
import com.example.ravin.exceptions.JwtSecurityException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtSecurityFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = jwtUtils.getTokenFromHeader(request).orElseThrow(() -> new JwtSecurityException("O token não foi encontrado"));
            String login = jwtUtils.validateToken(token);

            UserDetails user = userService.loadUserByUsername(login);

            if (user == null) {
                throw new EntityNotFoundException("Usuário não encontrado");
            }

            authenticate(user);

        } catch (JwtSecurityException | EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private void authenticate(UserDetails user) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
    }
}
