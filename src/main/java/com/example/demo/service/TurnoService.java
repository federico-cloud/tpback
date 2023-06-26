package com.example.demo.service;

import com.example.demo.persistence.entities.Turno;
import com.example.demo.persistence.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    @Autowired
    ITurnoRepository turnoRepository;

    public Turno add(Turno turno){
        return turnoRepository.save(turno);
    }

    public List<Turno> getAll(){
        return turnoRepository.findAll();
    }

}