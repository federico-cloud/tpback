package com.example.demo.controller;

import com.example.demo.dto.OdontologoDTO;
import com.example.demo.dto.PacienteDTO;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.service.OdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    OdontologoService odontologoService;

    //Listar todos los odontologos
    @GetMapping("/getAll")
    public ResponseEntity<List<OdontologoDTO>> listAll(){

        ResponseEntity<OdontologoDTO> response = null;

        ObjectMapper mapper = new ObjectMapper();
        List<Odontologo> odontologos = odontologoService.listAll();
        List<OdontologoDTO> odontologosDTO = new ArrayList<>();

        for(Odontologo odontologo : odontologos) {
            OdontologoDTO odontologoDto = mapper.convertValue(odontologo,OdontologoDTO.class);
            odontologosDTO.add(odontologoDto);
        }

        if (odontologosDTO.size() != 0){
            return ResponseEntity.ok(odontologosDTO);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/search/{id}")
    public ResponseEntity<OdontologoDTO> searchById(@PathVariable Long id){

        ResponseEntity<PacienteDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();
        Odontologo odontologo = odontologoService.findById(id);
        OdontologoDTO odontologoDto = null;

        if (odontologo != null){
            odontologoDto = mapper.convertValue(odontologo, OdontologoDTO.class);
            return ResponseEntity.ok(odontologoDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/add")
    public ResponseEntity<OdontologoDTO> addOdontologo(@RequestBody Odontologo odontologo){
        ResponseEntity<OdontologoDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();
        Odontologo odontologoClass = odontologoService.addOdontologo(odontologo);
        OdontologoDTO odontologoDTO = null;
        odontologoDTO = mapper.convertValue(odontologoClass, OdontologoDTO.class);

        return ResponseEntity.ok(odontologoDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OdontologoDTO> deleteOdontologo(@PathVariable Long id){
        ResponseEntity<OdontologoDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();
        Odontologo odontologo = odontologoService.findById(id);
//        OdontologoDTO odontologoDTO = null;

        if (odontologo != null) {
            odontologoService.deleteOdontologo(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<OdontologoDTO> modifyOdontologo(@PathVariable Long id, @RequestBody Odontologo odontologo) {
        ResponseEntity<OdontologoDTO> response = null;
        Odontologo odontologoModificar = odontologoService.findById(id);

        if (odontologoModificar != null){
            odontologoService.modifyOdontologo(id, odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
