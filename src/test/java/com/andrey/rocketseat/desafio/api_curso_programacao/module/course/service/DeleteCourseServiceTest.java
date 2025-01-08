package com.andrey.rocketseat.desafio.api_curso_programacao.module.course.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.DeleteCourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DeleteCourseServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private DeleteCourseService deleteCourseService;

    @Test
    @DisplayName("Should return that ID wasn't found")
    public void should_return_that_ID_was_not_found(){
        try{
            deleteCourseService.execute(UUID.randomUUID());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(EmptyResultDataAccessException.class);
            assertEquals("ID fornecido, não foi encontrado.", e.getMessage());
        }
    }
}
