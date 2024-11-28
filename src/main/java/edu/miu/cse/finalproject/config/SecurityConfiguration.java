package edu.miu.cse.finalproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity//we discuss it tomorrow
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable) // Disable CSRF if it's an API
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/register/**", "/api/v1/login/**").permitAll() // Public endpoints
                        .requestMatchers("/api/v1/home/**").hasAnyRole("ADMIN","MEMBER") // Admin-only resource
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .formLogin(form -> form
                        .loginPage("/api/v1/login") // Specify the custom login page
                        .defaultSuccessUrl("/api/v1/home", true) // Redirect to home on success
                        .failureUrl("/api/v1/login?error=true") // Redirect to login on failure
                        .permitAll() // Allow everyone to access the login page
                )
                .logout(logout -> logout
                        .logoutUrl("/api/v1/logout") // Specify logout URL
                        .logoutSuccessUrl("/api/v1/login?logout=true") // Redirect to login after logout
                        .invalidateHttpSession(true) // Invalidate session on logout
                        .deleteCookies("JSESSIONID") // Clear cookies
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Enable sessions for login
                )
                .authenticationProvider(authenticationProvider) // Use custom authentication logic
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter for token validation
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendRedirect("/api/v1/login")) // Redirect unauthenticated users to login
                );

        return http.build();
    }

}

