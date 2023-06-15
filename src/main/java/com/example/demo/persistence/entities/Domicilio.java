package com.example.demo.persistence.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "domicilio_id")
    private Long id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    @OneToMany(mappedBy = "domicilio")
    private Set<Paciente> pacientes = new HashSet<>();

}
