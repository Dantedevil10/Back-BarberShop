package com.example.dbarber.controller;

import com.example.dbarber.services.HorarioService;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dbarber.models.Horario;
import com.example.dbarber.dto.HorarioDTO;

@RestController
@RequestMapping("/horario")
@CrossOrigin("http://localhost:4200")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @PostMapping("/marcar")
    public ResponseEntity<Horario> marcarHorario(@RequestBody HorarioDTO horarioDTO) {
        if (horarioDTO.getStartAt() == null || horarioDTO.getEndAt() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(horarioService.marcarHorario(
            horarioDTO.getClienteId(), 
            horarioDTO.getStartAt(), 
            horarioDTO.getEndAt()
        ));
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<Horario> editarHorario(@PathVariable Long id, @RequestBody HorarioDTO horarioDTO) {
        return ResponseEntity.ok(horarioService.editarHorario(id, horarioDTO.getStartAt(), horarioDTO.getEndAt()));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirHorario(@PathVariable Long id) {
        horarioService.excluirHorario(id);
        return ResponseEntity.noContent().build();
    }
}
