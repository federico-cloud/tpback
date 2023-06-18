package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {

    //Attributes
    private String nombre;
    private String apellido;
//    private Domicilio domicilio;
    private Date fechaRegistro;


}
