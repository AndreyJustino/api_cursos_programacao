package com.andrey.rocketseat.desafio.api_curso_programacao.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenProviderTeacher {
    @Value("${security.token.secret}")
    private String secretKey;

    public DecodedJWT verifyToken(String token){
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try{
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return decodedJWT;

        } catch (JWTVerificationException e) {
            return null;
        }



    }
}
