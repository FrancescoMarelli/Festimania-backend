package org.nevent.festimania.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
                .sessionManagement(session -> session
                        .sessionCreationPolicy(STATELESS)) // Política de sesión stateless
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll() // Permitir la creación del primer usuario
                        .requestMatchers(HttpMethod.PUT,"/api/v1/festival/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/v1/festival/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/festival/**").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/api/v1/artista/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/v1/artista/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/artista/**").authenticated()
                        .anyRequest().permitAll()) // Permitir el resto de las solicitudes
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Añadir el filtro JWT
                .build();
    }


}
