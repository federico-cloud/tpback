package com.example.demo.controller;

import com.example.demo.dto.TurnoDTO;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.entities.Turno;
import com.example.demo.service.TurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoService turnoService;

    @PostMapping("/add")
    public ResponseEntity<TurnoDTO> addTurno(@RequestBody Turno turno){
        ResponseEntity<TurnoDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = turno.getPaciente();
        Odontologo odontologo = turno.getOdontologo();
        LocalDateTime fechaYhora = turno.getFechaYHora();

        Turno turnoClass = turnoService.add(turno);
        TurnoDTO turnoDTO = null;
        turnoDTO = mapper.convertValue(turnoClass,TurnoDTO.class);

        return ResponseEntity.ok(turnoDTO);
    }
}
