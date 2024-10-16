package com.nnamanibenjamin.E_commerce.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nnamanibenjamin.E_commerce.rest.api.repository.UserRepository;
import com.nnamanibenjamin.E_commerce.rest.api.services.JwtServices;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    final UserRepository userRepository;
    final JwtServices jwtServices; 

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/images/**").permitAll()
                .requestMatchers("/index.html").permitAll() 
                .requestMatchers("/api/auth").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/product/**").permitAll() 
                .requestMatchers("/api/auth/change-password").permitAll()
                .anyRequest().authenticated()
                ).sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);

                return http.build();
            }

            @Bean
            public jwtAuthenticationFilter jwtAuthenticationFilter(){
                return new jwtAuthenticationFilter(jwtServices,userDetailsService());
            }

            @Bean
            public PasswordEncoder passwordEncoder(){
                return new BCryptPasswordEncoder();
            }

            @Bean
            public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig){
                return authConfig.getAuthenticationManager();
            }

            @Bean
            public UserDetailServices userDetailsService(){
                
            }
    }
    

