package com.example.demo.persistence.entities;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id", nullable = false)
    private Domicilio domicilio;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente")



    private Date fechaRegistro;

}
