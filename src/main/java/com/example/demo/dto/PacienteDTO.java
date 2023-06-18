package com.example.demo.dto;

import com.example.demo.persistence.entities.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {

    //Attributes
    private String nombre;
    private String apellido;
    private String dni;
    private Domicilio domicilio;
    private Date fechaRegistro;

}
