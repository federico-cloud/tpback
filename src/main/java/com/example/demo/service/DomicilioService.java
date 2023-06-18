package com.example.demo.service;

import com.example.demo.persistence.entities.Domicilio;
import com.example.demo.persistence.repository.IDomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService {

    @Autowired
    IDomicilioRepository domicilioRepository;

    public List<Domicilio> getAll(){
        return domicilioRepository.findAll();
    }

    public Domicilio findById(Long id){
        return domicilioRepository.findById(id).orElse(null);
    }

    public Domicilio addDomicilio(Domicilio domicilio){
        return domicilioRepository.save(domicilio);
    }

    public void deleteDomicilio(Long id){
        domicilioRepository.deleteById(id);
    }

    //TODO
    public void modifyDomicilio() {

    }
}
