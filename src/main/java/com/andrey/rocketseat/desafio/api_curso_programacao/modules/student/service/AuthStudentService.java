package com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.exception.StudentNotFoundException;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.StudentEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto.AuthStudentResponseDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto.AuthStundentDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.repository.StudentRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthStudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretToken;

    public AuthStudentResponseDTO execute(AuthStundentDTO authStundentDTO) throws AuthException {
        StudentEntity student = this.studentRepository.findByEmail(authStundentDTO.getEmail()).orElseThrow(() -> {
            throw new StudentNotFoundException();
        });

        boolean passwordVerify = passwordEncoder.matches(authStundentDTO.getPassword(), student.getPassword());

        if(!passwordVerify){
            throw new AuthException("Senha invalida");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretToken);

        Instant expireIn = Instant.now().plus(Duration.ofHours(4));

        String token = JWT.create()
                .withSubject(student.getId().toString())
                .withClaim("roles", Arrays.asList("STUDENT"))
                .withExpiresAt(expireIn)
                .withIssuer("AndreyJustino") //nome de quem assina
                .sign(algorithm);

        return AuthStudentResponseDTO.builder()
                .token(token)
                .expireAt(expireIn)
                .build();

    }
}
