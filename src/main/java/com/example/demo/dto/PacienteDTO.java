package com.example.demo.dto;

import com.example.demo.persistence.entities.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {

    //Attributes
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Domicilio domicilio;

}
