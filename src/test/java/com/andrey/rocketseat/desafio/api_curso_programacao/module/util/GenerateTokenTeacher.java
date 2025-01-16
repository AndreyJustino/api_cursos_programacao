package com.andrey.rocketseat.desafio.api_curso_programacao.module.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

@Component
@ActiveProfiles("test")
public class GenerateTokenTeacher {

    private static String secretKey;

    public GenerateTokenTeacher(@Value("${security.token.secret}") String secretKey){
        this.secretKey = secretKey;
    };

    public static String execute(UUID id){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant expireIn = Instant.now().plus(Duration.ofMinutes(5));

        String token = JWT.create()
                .withClaim("roles", Arrays.asList("TEACHER"))
                .withSubject(id.toString())
                .withExpiresAt(expireIn)
                .sign(algorithm);

        return token;
    }
}
