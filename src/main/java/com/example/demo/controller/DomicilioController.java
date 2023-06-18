package com.example.demo.controller;

import com.example.demo.dto.DomicilioDTO;
import com.example.demo.persistence.entities.Domicilio;
import com.example.demo.service.DomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    DomicilioService domicilioService;

    @GetMapping("/getAll")
    public ResponseEntity<List<DomicilioDTO>> listAll(){

        ResponseEntity<DomicilioDTO> response = null;
        ObjectMapper mapper = new ObjectMapper();
        List<Domicilio> domicilios = domicilioService.getAll();
        List<DomicilioDTO> domiciliosDto = new ArrayList<>();

        for(Domicilio domicilio : domicilios) {
            DomicilioDTO domicilioDto = mapper.convertValue(domicilio,DomicilioDTO.class);
            domiciliosDto.add(domicilioDto);
        }

        if (domiciliosDto.size() != 0){
            return ResponseEntity.ok(domiciliosDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
