package com.example.T3.repositorys;

import com.example.T3.models.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    Optional<Provincia> findByNombre(String nombre);
}
