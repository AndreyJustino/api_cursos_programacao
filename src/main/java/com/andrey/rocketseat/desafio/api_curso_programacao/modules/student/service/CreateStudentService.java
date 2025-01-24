package com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.exception.StudentFoundException;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.StudentEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto.CreateStudentDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateStudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public StudentEntity execute(CreateStudentDTO createStudentDTO){
        this.studentRepository.findByEmailOrUsername(createStudentDTO.getEmail(), createStudentDTO.getUsername())
                .ifPresent(value -> {
                    throw new StudentFoundException();
            }
        );

        String password = passwordEncoder.encode(createStudentDTO.getPassword());

        StudentEntity student = StudentEntity.builder()
                .name(createStudentDTO.getName())
                .username(createStudentDTO.getUsername())
                .email(createStudentDTO.getEmail())
                .password(password)
                .build();

        return this.studentRepository.save(student);
    }
}
