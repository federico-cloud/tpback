package com.example.demo.controller;

import com.example.demo.dto.TurnoDTO;
import com.example.demo.exceptions.ResourceCouldNotBeAdded;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.entities.Turno;
import com.example.demo.service.OdontologoService;
import com.example.demo.service.PacienteService;
import com.example.demo.service.TurnoService;
import com.example.demo.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoService turnoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;

    @GetMapping("/getAll")
    public ResponseEntity <List<TurnoDTO>> getAll() throws ResourceNotFoundException {

        ObjectMapper mapper = new ObjectMapper();
        List<Turno> turnos = turnoService.getAll();
        List<TurnoDTO> turnosDto = new ArrayList<>();

        for(Turno turno : turnos) {
            TurnoDTO turnoDto = mapper.convertValue(turno,TurnoDTO.class);
            turnosDto.add(turnoDto);
        }

        return ResponseEntity.ok(turnosDto);
    }

    @PostMapping("/add")
    public ResponseEntity<TurnoDTO> addTurno(@RequestBody Turno turno) throws ResourceCouldNotBeAdded {

        ResponseEntity<TurnoDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();

        Paciente paciente = pacienteService.findById(turno.getPaciente().getId());
        Odontologo odontologo = odontologoService.findById(turno.getOdontologo().getId());

        turno.setFechaYHora(Util.utilDateToSqlDate(new Date()));
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        Turno turnoClass = turnoService.add(turno);
        TurnoDTO turnoDTO = null;
        turnoDTO = mapper.convertValue(turnoClass,TurnoDTO.class);

        return ResponseEntity.ok(turnoDTO);
    }
}
