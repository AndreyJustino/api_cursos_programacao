package com.andrey.rocketseat.desafio.api_curso_programacao.security;

import com.andrey.rocketseat.desafio.api_curso_programacao.provider.TokenProviderStudent;
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
public class SecurityFilterStudent extends OncePerRequestFilter {
    @Autowired
    private TokenProviderStudent tokenProviderStudent;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if(header != null){
            DecodedJWT token = this.tokenProviderStudent.verifyToken(header);

            if (token == null){

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                return;
            }

            String subject = token.getSubject();

            request.setAttribute("student_id", subject);

            List<Object> roles = token.getClaim("roles").asList(Object.class);

            List<SimpleGrantedAuthority> grants = roles.stream().map((value) -> {
                return new SimpleGrantedAuthority("ROLE_" + value.toString().toUpperCase());
            }).toList();

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
