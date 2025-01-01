package com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.repository;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    Optional<StudentEntity> findByEmailAndPassword(String email, String password);
    Optional<StudentEntity> findByEmailOrUsername(String email, String username);
}
