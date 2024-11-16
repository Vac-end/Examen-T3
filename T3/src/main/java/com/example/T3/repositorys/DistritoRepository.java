package com.example.T3.repositorys;

import com.example.T3.models.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistritoRepository extends JpaRepository<Distrito, Long> {
    Optional<Distrito> findByNombre(String nombre);
}
