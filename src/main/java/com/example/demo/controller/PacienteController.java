package com.example.demo.controller;

import com.example.demo.dto.OdontologoDTO;
import com.example.demo.dto.PacienteDTO;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/getAll")
    public ResponseEntity<List<PacienteDTO>> listAll(){

        ResponseEntity<OdontologoDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();
        List<Paciente> pacientes = pacienteService.listAll();
        List<PacienteDTO> pacientesDto = new ArrayList<>();

        for(Paciente paciente : pacientes) {
            PacienteDTO pacienteDto = mapper.convertValue(paciente,PacienteDTO.class);
            pacientesDto.add(pacienteDto);
        }

        if (pacientesDto.size() != 0){
            return ResponseEntity.ok(pacientesDto);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

}
