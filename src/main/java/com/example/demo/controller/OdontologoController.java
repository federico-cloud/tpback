package com.example.demo.controller;

import com.example.demo.dto.OdontologoDTO;
import com.example.demo.exceptions.ResourceCouldNotBeAdded;
import com.example.demo.exceptions.ResourceNotFoundException;
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
    public ResponseEntity<List<OdontologoDTO>> listAll() throws ResourceNotFoundException {

        ObjectMapper mapper = new ObjectMapper();
        List<Odontologo> odontologos = odontologoService.listAll();
        List<OdontologoDTO> odontologosDTO = new ArrayList<>();

        for(Odontologo odontologo : odontologos) {
            OdontologoDTO odontologoDto = mapper.convertValue(odontologo,OdontologoDTO.class);
            odontologosDTO.add(odontologoDto);
        }

        return ResponseEntity.ok(odontologosDTO);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<OdontologoDTO> searchById(@PathVariable Long id){

        ResponseEntity<OdontologoDTO> response = null;

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
    public ResponseEntity<OdontologoDTO> addOdontologo(@RequestBody Odontologo odontologo) throws ResourceCouldNotBeAdded {

        ObjectMapper mapper = new ObjectMapper();
        Odontologo odontologoClass = odontologoService.addOdontologo(odontologo);
        OdontologoDTO odontologoDTO = null;
        odontologoDTO = mapper.convertValue(odontologoClass, OdontologoDTO.class);

        return ResponseEntity.ok(odontologoDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOdontologo(@PathVariable Long id) throws ResourceNotFoundException {

        odontologoService.deleteOdontologo(id);
        return ResponseEntity.ok("Odontologo eliminado.");
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<String> modifyOdontologo(@PathVariable Long id, @RequestBody Odontologo odontologo) throws ResourceNotFoundException {

        Odontologo odontologoModificar = odontologoService.findById(id);

        odontologoService.modifyOdontologo(id, odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());

        return ResponseEntity.ok("El odontologo fue modificado exitosamente");
    }


}
