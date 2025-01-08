package com.andrey.rocketseat.desafio.api_curso_programacao.module.course.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.UpdatePartilService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UpdatePartilServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private UpdatePartilService updatePartilService;

    @Test
    @DisplayName("Should return course not found")
    public void should_return_course_not_found(){
        try {
            this.updatePartilService.updatePartilService(UUID.randomUUID(), null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
            assertEquals("ID fornecido, não encontrado", e.getMessage());
        }
    }
}
