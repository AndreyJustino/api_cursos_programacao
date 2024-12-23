package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    Optional<CourseEntity> findByNameAndCategory(String name, String category);
}
