package com.andrey.rocketseat.desafio.api_curso_programacao.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] SWAGGER_LIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf((value) -> value.disable())
            .authorizeHttpRequests((auth) -> {
                auth.requestMatchers("/cursos/").permitAll()
                     .requestMatchers("/cursos/{id}").permitAll()
                     .requestMatchers("/cursos/{id}/{status}").permitAll()
                     .requestMatchers("/student/register").permitAll()
                     .requestMatchers(SWAGGER_LIST).permitAll()
                     .anyRequest().authenticated();
            });
        
        return httpSecurity.build();
    }
    

    
}
