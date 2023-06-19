package com.example.demo.controller;

import com.example.demo.dto.TurnoDTO;
import com.example.demo.persistence.entities.Turno;
import com.example.demo.service.TurnoService;
import com.example.demo.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoService turnoService;

    @PostMapping("/add")
    public ResponseEntity<TurnoDTO> addTurno(@RequestBody Turno turno){
        ResponseEntity<TurnoDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();

        turno.setFechaYHora(Util.utilDateToSqlDate(new Date()));

        Turno turnoClass = turnoService.add(turno);
        TurnoDTO turnoDTO = null;
        turnoDTO = mapper.convertValue(turnoClass,TurnoDTO.class);

        return ResponseEntity.ok(turnoDTO);
    }
}
