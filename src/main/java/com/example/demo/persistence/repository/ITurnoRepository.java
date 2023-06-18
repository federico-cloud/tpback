package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
