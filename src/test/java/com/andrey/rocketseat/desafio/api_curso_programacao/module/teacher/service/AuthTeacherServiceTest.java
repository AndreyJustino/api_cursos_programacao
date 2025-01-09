package com.andrey.rocketseat.desafio.api_curso_programacao.module.teacher.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.exception.TeacherNotFoundException;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.dto.AuthTeacherRequestDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.repository.TeacherRepository;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.service.AuthTeacherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class AuthTeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthTeacherService authTeacherService;

    @Test
    @DisplayName("Should return that teacher was not found")
    public void should_return_that_teacher_was_not_found(){
        try{
            this.authTeacherService.execute(new AuthTeacherRequestDTO());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(TeacherNotFoundException.class);
        }
    }


}
