package com.example.ravin.config.security;

import com.example.ravin.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilter {
    protected static final Map<HttpMethod, List<String>> WHITELIST = Map.of(
            HttpMethod.GET, List.of(
                    "/product",
                    "/product/**"
            ),
            HttpMethod.POST, List.of(
                    "/auth/login",
                    "/auth/register",
                    "/auth/register/customer",
                    "/auth/register/employee"
            )
    );

    private final JwtSecurityFilter jwtSecurityFilter;
    private final AccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspection) throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspection);

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // WHITELIST
                        .requestMatchers(
                                WHITELIST.entrySet().stream()
                                        .flatMap(entry -> entry.getValue().stream().map(uri -> mvc.pattern(entry.getKey(), uri)))
                                        .toArray(RequestMatcher[]::new)
                        ).permitAll()

                        // CUSTOMERS
                        .requestMatchers(
                                mvc.pattern("/customer"),
                                mvc.pattern("/customer/**")
                        ).hasRole(UserRole.ADMIN.name())

                        // EMPLOYEES
                        .requestMatchers(
                                mvc.pattern("/employee"),
                                mvc.pattern("/employee/**")
                        ).hasRole(UserRole.ADMIN.name())

                        .requestMatchers(
                                mvc.pattern("/employee/available-waiters")
                        ).hasRole(UserRole.EMPLOYEE.name())

                        // PRODUCTS
                        .requestMatchers(
                                mvc.pattern(HttpMethod.POST, "/product"),
                                mvc.pattern(HttpMethod.PUT, "/product/**"),
                                mvc.pattern(HttpMethod.DELETE, "/product/**")
                        ).hasRole(UserRole.ADMIN.name())

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
