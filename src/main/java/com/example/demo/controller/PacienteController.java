package com.example.demo.controller;

import com.example.demo.dto.OdontologoDTO;
import com.example.demo.dto.PacienteDTO;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.service.PacienteService;
import com.example.demo.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/getAll")
    public ResponseEntity<List<PacienteDTO>> listAll(){

        ResponseEntity<OdontologoDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();
        List<Paciente> pacientes = pacienteService.getAll();
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
    @PostMapping("/add")
    public ResponseEntity<PacienteDTO> addPaciente(@RequestBody Paciente paciente){
        ResponseEntity<OdontologoDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();

        paciente.setFechaRegistro(Util.utilDateToSqlDate(Util.dateToTimestamp(new Date())));

        Paciente pacienteClass = pacienteService.add(paciente);
        PacienteDTO pacienteDto = null;
        pacienteDto = mapper.convertValue(pacienteClass, PacienteDTO.class);

        return ResponseEntity.ok(pacienteDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PacienteDTO> deletePaciente(@PathVariable Long id){
        ResponseEntity<PacienteDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = pacienteService.findById(id);

        if (paciente != null) {
            pacienteService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
