package com.example.demo.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;
    private String apellido;
    private String matricula;


}
