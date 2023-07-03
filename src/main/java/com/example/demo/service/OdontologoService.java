package com.example.demo.service;

import com.example.demo.exceptions.ResourceCouldNotBeAdded;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.repository.IOdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService{

    @Autowired
    IOdontologoRepository odontologoRepository;

    //Methods
    public List<Odontologo> listAll() throws ResourceNotFoundException {

        List<Odontologo> odontologos = odontologoRepository.findAll();

        if (odontologos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron odontologos");
        }

        return odontologos;
    }

    public Odontologo findById(Long id){
        return odontologoRepository.findById(id).orElse(null);
    }

    public Odontologo addOdontologo(Odontologo odontologo) throws ResourceCouldNotBeAdded {

        if (odontologo.getNombre().equals("") || odontologo.getApellido().equals("") || odontologo.getMatricula().equals("")){
            throw new ResourceCouldNotBeAdded("No se pudo agregar el odontólogo a la base de datos, el formato no es el correcto");
        }

        return odontologoRepository.save(odontologo);
    }

    public void deleteOdontologo(Long id) throws ResourceNotFoundException {

        if (findById(id) == null){
            throw new ResourceNotFoundException("No existe un odontologo con ese Id");
        }
        odontologoRepository.deleteById(id);
    }

    public void modifyOdontologo(Long id, String nombre, String apellido, String matricula) throws ResourceNotFoundException{

        if (findById(id) == null){
            throw new ResourceNotFoundException("No existe un odontologo con ese Id");
        }

        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);

        odontologo.setNombre(nombre);
        odontologo.setApellido(apellido);
        odontologo.setMatricula(matricula);

        odontologoRepository.save(odontologo);

    }

}
