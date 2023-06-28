package com.example.demo.persistence.entities;

import lombok.Data;


import javax.persistence.*;
import java.util.*;

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

    @OneToMany(mappedBy = "paciente")
    @Column(name = "turno_id")
    private Set<Turno> turnos = new HashSet<>();

    private Date fechaRegistro;

}
