package com.andrey.rocketseat.desafio.api_curso_programacao.module.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.module.util.GenerateTokenTeacher;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.DeleteCourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class DeleteCourseControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

//    @InjectMocks
//    private DeleteCourseService deleteCourseService;

    @MockitoBean
    private CourseRepository courseRepository;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("Should delete course already registered")
    public void should_delete_course_already_registered() throws Exception {

        UUID id = UUID.randomUUID();

        when(this.courseRepository.findById(id)).thenReturn(Optional.of(new CourseEntity()));

        var result = mvc.perform(
                MockMvcRequestBuilders.delete("/cursos/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", GenerateTokenTeacher.execute(UUID.randomUUID()))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
