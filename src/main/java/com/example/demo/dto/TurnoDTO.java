package com.example.demo.dto;

import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDTO {
    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date fechaYHora;
}