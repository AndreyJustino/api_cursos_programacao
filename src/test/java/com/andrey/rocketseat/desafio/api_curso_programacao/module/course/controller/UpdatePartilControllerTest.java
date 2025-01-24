package com.andrey.rocketseat.desafio.api_curso_programacao.module.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.module.util.GenerateTokenTeacher;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import lombok.val;
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
public class UpdatePartilControllerTest {
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
    @DisplayName("Should return that ID not found")
    public void should_return_that_id_not_found() throws Exception {

        UUID id = UUID.randomUUID();
        String status = "ATIVADO";

        String url = "/cursos/" + id + "/" + status;

        mvc.perform(MockMvcRequestBuilders.patch(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", GenerateTokenTeacher.execute(UUID.randomUUID()))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("ID fornecido, não encontrado"));

    }

    @Test
    @DisplayName("Should return that the course status has been updated.")
    public void e() throws Exception {
        UUID id = UUID.randomUUID();
        String status = "ATIVADO";

        String url = "/cursos/" + id + "/" + status;

        when(this.courseRepository.findById(id)).thenReturn(Optional.of(new CourseEntity()));

        mvc.perform(MockMvcRequestBuilders.patch(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", GenerateTokenTeacher.execute(UUID.randomUUID()))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.message").value("Status do curso atualizado com sucesso!"));
    }


}
