package com.andrey.rocketseat.desafio.api_curso_programacao.module.student.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.exception.StudentNotFoundException;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.StudentEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto.AuthStundentDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.repository.StudentRepository;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.service.AuthStudentService;
import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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
}
