package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.Status;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.MessageReturnDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.UpdatePartilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class UpdatePartilController {
    @Autowired
    private UpdatePartilService updatePartilService;

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<Object> updatePartilController(@PathVariable UUID id, @PathVariable String status){
        try{

            Status enumStatus = Status.valueOf(status.toUpperCase());

            CourseEntity courseEntity = updatePartilService.updatePartilService(id, enumStatus);

            MessageReturnDTO messageReturnDTO = MessageReturnDTO.builder()
                    .message("Statu do curso atualizado com sucesso!")
                    .object(courseEntity)
                    .build();

            return ResponseEntity.ok().body(messageReturnDTO);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
