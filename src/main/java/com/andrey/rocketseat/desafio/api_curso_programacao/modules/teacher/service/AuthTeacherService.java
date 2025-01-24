package com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.exception.TeacherNotFoundException;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto.AuthStudentResponseDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.TeacherEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.dto.AuthTeacherRequestDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.dto.AuthTeacherResponseDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.repository.TeacherRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class AuthTeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    public AuthTeacherResponseDTO execute(AuthTeacherRequestDTO authTeacherRequestDTO){


        TeacherEntity teacher = this.teacherRepository.findByEmail(authTeacherRequestDTO.getEmail())
                .orElseThrow(() -> {
                    throw new TeacherNotFoundException();
                });

        boolean password = passwordEncoder.matches(authTeacherRequestDTO.getPassword(), teacher.getPassword());

        if (!password){
            throw new IllegalArgumentException("A senha fornecida não esta correta.");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant expireIn = Instant.now().plus(Duration.ofHours(4));

        String token = JWT.create()
                .withClaim("roles", Arrays.asList("TEACHER"))
                .withSubject(teacher.getId().toString())
                .withExpiresAt(expireIn)
                .sign(algorithm);

        return AuthTeacherResponseDTO.builder()
                .token(token)
                .expireAt(expireIn)
                .build();
    }

}
