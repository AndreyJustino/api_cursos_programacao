package com.andrey.rocketseat.desafio.api_curso_programacao.module.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.module.util.GenerateJson;
import com.andrey.rocketseat.desafio.api_curso_programacao.module.util.GenerateTokenTeacher;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.Status;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.TeacherEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateCourseControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("Should to be able to create a new course")
    public void should_to_be_able_to_create_a_new_course() throws Exception {
        TeacherEntity teacher = new TeacherEntity();
        teacher.setId(UUID.randomUUID());

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName("NAME TEST");
        courseEntity.setCategory("CATEGORY TEST");
        courseEntity.setStatus(Status.ATIVADO);

        when(this.courseRepository.save(courseEntity)).thenReturn(courseEntity);

        var result = mvc.perform(
                MockMvcRequestBuilders.post("/cursos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(GenerateJson.execute(courseEntity))
                        .header("Authorization", GenerateTokenTeacher.execute(teacher.getId()))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
