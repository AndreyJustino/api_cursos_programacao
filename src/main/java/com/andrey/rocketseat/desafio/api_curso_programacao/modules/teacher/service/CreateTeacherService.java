package com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.exception.TeacherFoundExecption;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.TeacherEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.dto.CreateTeacherDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateTeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TeacherEntity execute(CreateTeacherDTO createTeacherDTO){

        this.teacherRepository
                .findByEmailOrUsername(createTeacherDTO.getEmail(), createTeacherDTO.getUsername())
                    .ifPresent(value -> {
                        throw new TeacherFoundExecption();
                    });

        String password = passwordEncoder.encode(createTeacherDTO.getPassword());

        TeacherEntity teacherEntity = TeacherEntity.builder()
                .name(createTeacherDTO.getName())
                .username(createTeacherDTO.getUsername())
                .email(createTeacherDTO.getEmail())
                .password(password)
                .build();

        return this.teacherRepository.save(teacherEntity);
    }
}
