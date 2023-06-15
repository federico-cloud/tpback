package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoDTO {
    //Attributes
    private String nombre;
    private String apellido;
    private String matricula;


}
