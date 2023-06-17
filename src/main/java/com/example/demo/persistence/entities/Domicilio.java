package com.example.demo.persistence.entities;

import jakarta.persistence.*;

@Entity
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;


}
