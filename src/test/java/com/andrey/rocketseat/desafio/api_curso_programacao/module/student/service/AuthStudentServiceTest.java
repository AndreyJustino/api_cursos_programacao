package com.andrey.rocketseat.desafio.api_curso_programacao.module.student.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.exception.StudentNotFoundException;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.StudentEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto.AuthStudentResponseDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto.AuthStundentDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.repository.StudentRepository;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.service.AuthStudentService;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthStudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthStudentService authStudentService;

    @BeforeEach
    void setUp(){
        //configurando valor do secret (injetando valores em atributos privados)
        ReflectionTestUtils.setField(authStudentService, "secretToken", "secretTokenJwt");
    }

    @Test
    @DisplayName("Should return student not found")
    public void should_return_student_not_found(){
        AuthStundentDTO authStundentDTO = new AuthStundentDTO();
        authStundentDTO.setEmail("EMAIL TEST");

        try {
            this.authStudentService.execute(authStundentDTO);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(StudentNotFoundException.class);
        }

    }

    @Test
    @DisplayName("Should return that password is invalid")
    public void should_return_that_password_is_invalid(){
        AuthStundentDTO authStundentDTO = new AuthStundentDTO();
        authStundentDTO.setEmail("emailTeste@mail.com");

        when(this.studentRepository.findByEmail(authStundentDTO.getEmail())).thenReturn(Optional.of(new StudentEntity()));

        when(this.passwordEncoder.matches(any(),any()))
                .thenReturn(false);

        try {
            this.authStudentService.execute(authStundentDTO);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(AuthException.class);
            assertEquals("Senha invalida", e.getMessage());
        }
    }

    @Test
    @DisplayName("Should return DTO with token and time to expire")
    public void should_return_dto_with_token_and_time_to_expire() throws AuthException {
        AuthStundentDTO authStundentDTO = new AuthStundentDTO();
        authStundentDTO.setEmail("emailTeste@mail.com");

        StudentEntity student = new StudentEntity();
        student.setId(UUID.randomUUID());

        when(this.studentRepository.findByEmail(authStundentDTO.getEmail())).thenReturn(Optional.of(student));

        when(this.passwordEncoder.matches(any(),any()))
                .thenReturn(true);

        AuthStudentResponseDTO result = this.authStudentService.execute(authStundentDTO);

        assertThat(result).isInstanceOf(AuthStudentResponseDTO.class);
        assertThat(result).isNotNull();
        assertThat(result.getToken()).isNotNull();
        assertThat(result.getExpireAt()).isNotNull();
    }

}
