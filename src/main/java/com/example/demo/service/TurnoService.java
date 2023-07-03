package com.example.demo.service;

import com.example.demo.exceptions.ResourceCouldNotBeAdded;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.persistence.entities.Turno;
import com.example.demo.persistence.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    @Autowired
    ITurnoRepository turnoRepository;

    public Turno add(Turno turno) throws ResourceCouldNotBeAdded {

        if (turno.getOdontologo().getId() == null || turno.getPaciente().getId() == null){
            throw new ResourceCouldNotBeAdded("No se pudo agregar el odontólogo a la base de datos, el formato no es correcto");
        }
        return turnoRepository.save(turno);
    }

    public List<Turno> getAll() throws ResourceNotFoundException{
        return turnoRepository.findAll();
    }
}