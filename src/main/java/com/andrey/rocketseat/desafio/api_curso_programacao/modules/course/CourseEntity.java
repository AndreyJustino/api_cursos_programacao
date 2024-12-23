package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity(name = "course")
public class CourseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo (nome) não deve estar vazio")
    private String name;

    @NotBlank(message = "O campo (categoria) não deve estar vazio")
    private String category;

    @NotNull(message = "O campo (status) não deve estar vazio")
    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private LocalDateTime created_at;
    
    private LocalDateTime updated_at;
}
