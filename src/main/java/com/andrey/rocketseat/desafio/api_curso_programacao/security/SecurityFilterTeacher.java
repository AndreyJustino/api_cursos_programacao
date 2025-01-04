package com.andrey.rocketseat.desafio.api_curso_programacao.security;

import com.andrey.rocketseat.desafio.api_curso_programacao.provider.TokenProviderTeacher;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilterTeacher extends OncePerRequestFilter {
    @Autowired
    private TokenProviderTeacher tokenProviderTeacher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null){
            DecodedJWT token = this.tokenProviderTeacher.verifyToken(header);

            if (token == null){

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                return;
            }

            String subject = token.getSubject();

            request.setAttribute("teacher_id", subject);

            List<Object> roles = token.getClaim("roles").asList(Object.class);

            List<SimpleGrantedAuthority> grant = roles.stream().map((value) -> {
                return new SimpleGrantedAuthority("ROLE_" + value.toString().toUpperCase());
            }).toList();

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(token.getSubject(), null, grant);

            SecurityContextHolder.getContext().setAuthentication(auth);
        };

        filterChain.doFilter(request, response);
    }
}
