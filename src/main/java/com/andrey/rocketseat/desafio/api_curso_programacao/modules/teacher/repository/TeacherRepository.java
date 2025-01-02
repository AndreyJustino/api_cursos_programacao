package com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.repository;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<TeacherEntity, UUID> {
    Optional<TeacherEntity> findByEmailOrUsername(String email, String username);

    Optional<TeacherEntity> findByEmail(String email);
}
