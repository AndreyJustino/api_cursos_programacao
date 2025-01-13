package com.andrey.rocketseat.desafio.api_curso_programacao.module.teacher.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.exception.TeacherFoundExecption;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.TeacherEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.dto.CreateTeacherDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.repository.TeacherRepository;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.service.CreateTeacherService;
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
public class CreateTeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateTeacherService createTeacherService;

    @Test
    @DisplayName("Should return teacher already registered")
    public void should_return_teacher_already_registered(){

        when(teacherRepository.findByEmailOrUsername(any(), any())).thenReturn(Optional.of(new TeacherEntity()));

        try{
            this.createTeacherService.execute(new CreateTeacherDTO());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(TeacherFoundExecption.class);
            assertEquals("Professor ja cadastrado.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Should return the class entity TeacherEntity")
    public void should_return_the_class_entity_teacherentity(){
        CreateTeacherDTO createTeacherDTO = new CreateTeacherDTO();
        createTeacherDTO.setPassword("PASSWORD TEST");

        when(this.teacherRepository.save(new TeacherEntity())).thenReturn(new TeacherEntity());

        var result = this.createTeacherService.execute(createTeacherDTO);

        assertThat(result).isInstanceOf(TeacherEntity.class);
        assertThat(result).hasFieldOrProperty("name");
        assertThat(result).hasFieldOrProperty("username");
        assertThat(result).hasFieldOrProperty("email");
        assertThat(result).hasFieldOrProperty("password");

    }
}
