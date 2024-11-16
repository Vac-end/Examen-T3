package com.example.T3.controllers;

import com.example.T3.models.Departamento;
import com.example.T3.models.Distrito;
import com.example.T3.models.Documento;
import com.example.T3.models.Provincia;
import com.example.T3.models.dto.*;
import com.example.T3.services.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reclamos")
public class ReclamoController {
    private final ReclamoService reclamoService;

    @Autowired
    public ReclamoController(ReclamoService reclamoService) {
        this.reclamoService = reclamoService;
    }

    // Obtener todos los reclamos
    @GetMapping
    public ResponseEntity<List<ReclamoResponse>> getAllReclamos() {
        return reclamoService.getAll();
    }

    // Crear un nuevo reclamo
    @PostMapping
    public ResponseEntity<Map<String, Object>> createReclamo(@RequestBody ReclamoRequest reclamoRequest) {
        return reclamoService.create(reclamoRequest);
    }

    @GetMapping("/documento")
    public List<DocumentoResponse> getTiposDocumentos() {
        return reclamoService.getTiposDocumentos();
    }

    // Obtener los departamentos
    @GetMapping("/departamentos")
    public List<DepartamentoResponse> getDepartamentos() {
        return reclamoService.getDepartamentos();
    }

    // Obtener las provincias
    @GetMapping("/provincias")
    public List<ProvinciaResponse> getProvincias() {
        return reclamoService.getProvincias();
    }

    // Obtener los distritos
    @GetMapping("/distritos")
    public List<DistritoResponse> getDistritos() {
        return reclamoService.getDistritos();
    }
}
