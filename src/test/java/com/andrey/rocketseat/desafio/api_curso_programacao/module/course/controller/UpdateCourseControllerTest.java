package com.andrey.rocketseat.desafio.api_curso_programacao.module.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.module.util.GenerateJson;
import com.andrey.rocketseat.desafio.api_curso_programacao.module.util.GenerateTokenTeacher;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.Status;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.UpdateCourseDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UpdateCourseControllerTest {
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
    @DisplayName("Should return that the course not found")
    public void should_return_that_the_course_not_found() throws Exception {
        UUID id = UUID.randomUUID();

        UpdateCourseDTO updateCourseDTO = UpdateCourseDTO.builder()
                .name("COURSE TEST")
                .category("CATEGORY TEST")
                .status(Status.ATIVADO)
                .build();

        mvc.perform(MockMvcRequestBuilders.put("/cursos/"+ id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", GenerateTokenTeacher.execute(id))
                .content(GenerateJson.execute(updateCourseDTO))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("Curso não encontrado"));
    }

    @Test
    @DisplayName("Should return course update with sucess")
    public void should_return_course_update_with_sucess() throws Exception {
        UUID id = UUID.randomUUID();

        UpdateCourseDTO updateCourseDTO = UpdateCourseDTO.builder()
                .name("COURSE TEST")
                .category("CATEGORY TEST")
                .status(Status.ATIVADO)
                .build();

        when(this.courseRepository.findById(id)).thenReturn(Optional.of(new CourseEntity()));

        mvc.perform(MockMvcRequestBuilders.put("/cursos/"+ id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", GenerateTokenTeacher.execute(id))
                        .content(GenerateJson.execute(updateCourseDTO))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.message").value("Curso atualizado com sucesso!"));
    }
}
