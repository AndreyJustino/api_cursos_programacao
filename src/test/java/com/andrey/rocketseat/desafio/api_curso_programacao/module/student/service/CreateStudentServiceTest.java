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

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateStudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

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
}
