package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
}
