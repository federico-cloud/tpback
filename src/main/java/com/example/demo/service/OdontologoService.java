package com.example.demo.service;

import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.repository.IOdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService{

    @Autowired
    IOdontologoRepository odontologoRepository;

    //Methods
    public List<Odontologo> listAll(){
        return odontologoRepository.findAll();
    }

    public Odontologo findById(Long id){
        return odontologoRepository.findById(id).orElse(null);
    }

    public Odontologo addOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public void deleteOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }

    public void modifyOdontologo(Long id, String nombre, String apellido, String matricula) {
        Optional<Odontologo> optionalOdontologo = odontologoRepository.findById(id);
        if (optionalOdontologo.isPresent()) {
            Odontologo odontologo = optionalOdontologo.get();
            odontologo.setNombre(nombre);
            odontologo.setApellido(apellido);
            odontologo.setMatricula(matricula);
            odontologoRepository.save(odontologo);
        }
    }



}
