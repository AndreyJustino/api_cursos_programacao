package com.andrey.rocketseat.desafio.api_curso_programacao.module.course.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.CreateCourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CreateCourseService createCourseService;

    @Test
    @DisplayName("Should return course already register")
    public void should_return_course_already_register(){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName("NAME TEST");
        courseEntity.setCategory("CATEGORY TEST");

        when(this.courseRepository.findByNameAndCategory(courseEntity.getName(), courseEntity.getCategory()))
                .thenReturn(Optional.of(courseEntity));

        try{
            this.createCourseService.execute(courseEntity);
        } catch (Exception e) {
            assertEquals("Curso ja cadastrado!", e.getMessage());
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
