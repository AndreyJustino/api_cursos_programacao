package com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.repository;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    Optional<StudentEntity> findByEmailOrUsername(String email, String username);

    Optional<StudentEntity> findByEmail(String email);
}
