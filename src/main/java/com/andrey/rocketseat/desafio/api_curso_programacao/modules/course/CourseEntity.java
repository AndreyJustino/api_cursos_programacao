package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity(name = "course")
@DynamicUpdate
public class CourseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo (nome) não deve estar vazio")
    @Schema(example = "Curso de Java", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "O campo (categoria) não deve estar vazio")
    @Schema(example = "Back-end", requiredMode = Schema.RequiredMode.REQUIRED)
    private String category;

    @NotNull(message = "O campo (status) não deve estar vazio")
    @Enumerated(EnumType.STRING)
    @Schema(example = "ATIVADO", requiredMode = Schema.RequiredMode.REQUIRED)
    private Status status;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;
}
