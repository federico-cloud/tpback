package com.example.demo.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
    private Paciente paciente;
}
