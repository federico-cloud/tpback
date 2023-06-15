package com.example.demo.service;

import com.example.demo.persistence.entities.Domicilio;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    IPacienteRepository pacienteRepository;

    public List<Paciente> getAll(){
        return pacienteRepository.findAll();
    }

    public Paciente add(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public void delete(Long id){
        pacienteRepository.deleteById(id);
    }

    public Paciente findById(Long id){
        return pacienteRepository.findById(id).orElse(null);
    }

    public void modifiy(Long id, String nombre, String apellido, String dni, Date fechaAlta){
        Paciente paciente = pacienteRepository.findById(id).orElse(null);

        if (paciente != null && domicilio != null){
            paciente.setNombre(nombre);
            paciente.setApellido(apellido);
            paciente.set
        }
    }

}
