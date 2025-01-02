package com.andrey.rocketseat.desafio.api_curso_programacao.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] SWAGGER_LIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**"
    };

    @Autowired
    private SecurityFilterStudent securityFilterStudent;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf((value) -> value.disable())
            .authorizeHttpRequests((auth) -> {
                auth.requestMatchers("/cursos/{id}").permitAll()
                     .requestMatchers("/cursos/{id}/{status}").permitAll()
                     .requestMatchers("/student/register").permitAll()
                     .requestMatchers("/student/auth").permitAll()
                     .requestMatchers(SWAGGER_LIST).permitAll()
                     .anyRequest().authenticated();
            }).addFilterBefore(securityFilterStudent, BasicAuthenticationFilter.class);
        
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    

    
}
