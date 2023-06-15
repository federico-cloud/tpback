package com.example.demo.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Paciente {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String nombre;
    private String apellido;
    private String dni;

    @ManyToOne
    @JoinColumn(name = "domicilio_id", nullable = false)
    private Domicilio domicilio;

    private Date fechaRegistro;

}
