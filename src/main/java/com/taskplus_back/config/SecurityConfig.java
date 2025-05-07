package com.taskplus_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()  // Libera o H2 Console
                        .anyRequest().authenticated()  // Exige autenticação para outras rotas
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")  // Desativa CSRF para o H2
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame
                                .sameOrigin()  // Permite iframes do H2
                        )
                );

        return http.build();
    }
}