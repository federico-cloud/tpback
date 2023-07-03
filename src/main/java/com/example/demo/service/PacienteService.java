package com.example.demo.service;

import com.example.demo.exceptions.ResourceCouldNotBeAdded;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.persistence.entities.Domicilio;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    IPacienteRepository pacienteRepository;

    public List<Paciente> getAll() throws ResourceNotFoundException {
        List<Paciente> pacientes = pacienteRepository.findAll();

        if (pacientes.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron pacientes");
        }

        return pacientes;
    }


    public Paciente add(Paciente paciente) throws ResourceCouldNotBeAdded{
        if (paciente.getNombre().equals("") || paciente.getApellido().equals("") || paciente.getDni().equals("") || paciente.getDomicilio() == null){
            throw new ResourceCouldNotBeAdded("No se pudo agregar el odontólogo a la base de datos, el formato no es el correcto");
        }
        return pacienteRepository.save(paciente);
    }

    public void delete(Long id) throws ResourceNotFoundException {
        if (findById(id) == null){
            throw new ResourceNotFoundException("No existe un paciente con ese Id");
        }
        pacienteRepository.deleteById(id);
    }

    public Paciente findById(Long id){
        return pacienteRepository.findById(id).orElse(null);
    }

    public void modify(Long id, String nombre, String apellido, String dni, Domicilio domicilio) throws ResourceNotFoundException {

        if (findById(id) == null){
            throw new ResourceNotFoundException("No existe un paciente con ese Id");
        }

        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setDni(dni);
        paciente.setDomicilio(domicilio);

        pacienteRepository.save(paciente);

    }
}
