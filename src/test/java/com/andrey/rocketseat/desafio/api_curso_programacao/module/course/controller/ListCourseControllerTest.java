package com.andrey.rocketseat.desafio.api_curso_programacao.module.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.module.util.GenerateTokenTeacher;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ListCourseControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private CourseRepository courseRepository;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("Should return list of course")
    public void should_return_list_of_course() throws Exception {
        var result = mvc.perform(
                MockMvcRequestBuilders.get("/cursos/")
                        .header("Authorization", GenerateTokenTeacher.execute(UUID.randomUUID()))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
