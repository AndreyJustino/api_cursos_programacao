package com.andrey.rocketseat.desafio.api_curso_programacao.module.student.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.exception.StudentFoundException;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.StudentEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto.CreateStudentDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.repository.StudentRepository;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.service.CreateStudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateStudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateStudentService createStudentService;

    @Test
    @DisplayName("Should return that student is already registered")
    public void should_return_that_student_is_already_registered(){
        when(this.studentRepository.findByEmailOrUsername(anyString(), anyString())).thenReturn(
                Optional.of(new StudentEntity())
        );

        try{
            CreateStudentDTO createStudentDTO = new CreateStudentDTO();
            createStudentDTO.setEmail("emailTest@mail.com");
            createStudentDTO.setUsername("USERNAME TEST");

            this.createStudentService.execute(createStudentDTO);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(StudentFoundException.class);
        }

    }

    @Test
    @DisplayName("Should return the student entity")
    public void should_return_the_student_entity(){
        CreateStudentDTO createStudentDTO = new CreateStudentDTO();
        createStudentDTO.setPassword("PASSWORD TEST");

        when(this.studentRepository.save(new StudentEntity())).thenReturn(new StudentEntity());

        var result = this.createStudentService.execute(createStudentDTO);

        assertThat(result).isInstanceOf(StudentEntity.class);
        assertThat(result).hasFieldOrProperty("name");
        assertThat(result).hasFieldOrProperty("username");
        assertThat(result).hasFieldOrProperty("email");
        assertThat(result).hasFieldOrProperty("password");

    }
}
