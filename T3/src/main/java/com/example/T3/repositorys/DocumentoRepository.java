package com.example.T3.repositorys;

import com.example.T3.models.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    Optional<Documento> findByNombre(String nombre);
}
