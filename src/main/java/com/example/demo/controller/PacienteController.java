package com.example.demo.controller;

import com.example.demo.dto.PacienteDTO;
import com.example.demo.exceptions.ResourceCouldNotBeAdded;
import com.example.demo.exceptions.ResourceNotFoundException;
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
    public ResponseEntity<List<PacienteDTO>> listAll() throws ResourceNotFoundException {

        ObjectMapper mapper = new ObjectMapper();
        List<Paciente> pacientes = pacienteService.getAll();
        List<PacienteDTO> pacientesDto = new ArrayList<>();

        for(Paciente paciente : pacientes) {
            PacienteDTO pacienteDto = mapper.convertValue(paciente,PacienteDTO.class);
            pacientesDto.add(pacienteDto);
        }

        return ResponseEntity.ok(pacientesDto);

    }

    @GetMapping("/search/{id}")
    public ResponseEntity<PacienteDTO> searchById(@PathVariable Long id){

        ResponseEntity<PacienteDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = pacienteService.findById(id);
        PacienteDTO pacienteDto = null;

        if (paciente != null){
            pacienteDto = mapper.convertValue(paciente, PacienteDTO.class);
            return ResponseEntity.ok(pacienteDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/add")
    public ResponseEntity<PacienteDTO> addPaciente(@RequestBody Paciente paciente) throws ResourceCouldNotBeAdded {

        ObjectMapper mapper = new ObjectMapper();

        paciente.setFechaRegistro(Util.utilDateToSqlDate(Util.dateToTimestamp(new Date())));

        Paciente pacienteClass = pacienteService.add(paciente);
        PacienteDTO pacienteDto = null;
        pacienteDto = mapper.convertValue(pacienteClass, PacienteDTO.class);

        return ResponseEntity.ok(pacienteDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.delete(id);
        return ResponseEntity.ok("Paciente eliminado.");
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<String> modify(@PathVariable Long id, @RequestBody Paciente paciente) throws ResourceNotFoundException {

        Paciente pacienteModificar = pacienteService.findById(id);

        pacienteService.modify(id, paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getDomicilio());

        return ResponseEntity.ok("El paciente fue modificado exitosamente");

    }
}
