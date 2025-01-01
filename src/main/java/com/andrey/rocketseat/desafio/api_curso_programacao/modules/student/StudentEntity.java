package com.andrey.rocketseat.desafio.api_curso_programacao.modules.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo (name) não pode estar vazio")
    @Schema(example = "Andrey Justino", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "O campo (username) não pode estar vazio")
    @Schema(example = "andreyJustino", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Email(message = "O campo (email) precisa de um e-mail valido")
    @NotBlank(message = "O campo (email) não pode estar vazio")
    @Schema(example = "andrey@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Length(max = 70, min = 8, message = "A senha deve ter no minimo 8 carateres")
    @Schema(example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;
}
