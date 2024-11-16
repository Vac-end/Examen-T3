package com.example.T3.services;

import com.example.T3.models.*;
import com.example.T3.models.dto.*;
import com.example.T3.repositorys.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReclamoService {
    private final ReclamoRepository reclamoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;
    private final DocumentoRepository documentoRepository;

    public ReclamoService(ReclamoRepository reclamoRepository,
                          DepartamentoRepository departamentoRepository,
                          ProvinciaRepository provinciaRepository,
                          DistritoRepository distritoRepository,
                          DocumentoRepository documentoRepository) {
        this.reclamoRepository = reclamoRepository;
        this.departamentoRepository = departamentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.distritoRepository = distritoRepository;
        this.documentoRepository = documentoRepository;
    }

    public ResponseEntity<Map<String, Object>> create(ReclamoRequest reclamoRequest) {
        Map<String, Object> res = new HashMap<>();

        Departamento departamento = departamentoRepository.findById(reclamoRequest.departamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Departamento no encontrado"));

        Provincia provincia = provinciaRepository.findById(reclamoRequest.provinciaId())
                .orElseThrow(() -> new EntityNotFoundException("Provincia no encontrada"));

        Distrito distrito = distritoRepository.findById(reclamoRequest.distritoId())
                .orElseThrow(() -> new EntityNotFoundException("Distrito no encontrado"));

        Documento documento = documentoRepository.findById(reclamoRequest.tipoDocumentoId())
                .orElseThrow(() -> new EntityNotFoundException("Documento no encontrado"));

        // Crear el objeto Reclamo
        Reclamo nuevoReclamo = new Reclamo();
        nuevoReclamo.setNombreCompleto(reclamoRequest.nombreCompleto());
        nuevoReclamo.setDocumento(reclamoRequest.documento());
        nuevoReclamo.setDomicilio(reclamoRequest.domicilio());
        nuevoReclamo.setCorreoElectronico(reclamoRequest.correoElectronico());
        nuevoReclamo.setTelefono(reclamoRequest.telefono());
        nuevoReclamo.setCelular(reclamoRequest.celular());
        nuevoReclamo.setFechaHecho(reclamoRequest.fechaHecho());
        nuevoReclamo.setFuncionario(reclamoRequest.funcionario());
        nuevoReclamo.setDescripcion(reclamoRequest.descripcion());
        nuevoReclamo.setDepartamento(departamento);
        nuevoReclamo.setProvincia(provincia);
        nuevoReclamo.setDistrito(distrito);
        nuevoReclamo.setTipoDocumento(documento);

        // Guardar el nuevo reclamo
        Reclamo reclamoGuardado = reclamoRepository.save(nuevoReclamo);

        // Crear la respuesta
        res.put("reclamo", mapToReclamoResponse(reclamoGuardado));
        return ResponseEntity.ok(res);
    }

    // Obtener todos los reclamos
    public ResponseEntity<List<ReclamoResponse>> getAll() {
        List<Reclamo> listaReclamos = reclamoRepository.findAll();
        List<ReclamoResponse> response = listaReclamos.stream().map(this::mapToReclamoResponse).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private ReclamoResponse mapToReclamoResponse(Reclamo reclamo) {
        Long tipoDocumentoNombre = reclamo.getTipoDocumento() != null ? reclamo.getTipoDocumento().getId() : null;
        Long departamentoNombre = reclamo.getDepartamento() != null ? reclamo.getDepartamento().getId() : null;
        Long provinciaNombre = reclamo.getProvincia() != null ? reclamo.getProvincia().getId() : null;
        Long distritoNombre = reclamo.getDistrito() != null ? reclamo.getDistrito().getId() : null;

        return new ReclamoResponse(
                reclamo.getId(),
                reclamo.getNombreCompleto(),
                reclamo.getDocumento(),
                tipoDocumentoNombre,
                departamentoNombre,
                provinciaNombre,
                distritoNombre,
                reclamo.getDomicilio(),
                reclamo.getCorreoElectronico(),
                reclamo.getTelefono(),
                reclamo.getCelular(),
                reclamo.getFechaHecho(),
                reclamo.getFuncionario(),
                reclamo.getDescripcion()
        );
    }

    public List<DocumentoResponse> getTiposDocumentos() {
        return documentoRepository.findAll().stream().map(this::mapToDocumentoResponse).toList();
    }

    public List<DepartamentoResponse> getDepartamentos() {
        return departamentoRepository.findAll().stream().map(this::mapToDepartamentoResponse).toList();
    }

    public List<ProvinciaResponse> getProvincias() {
        return provinciaRepository.findAll().stream().map(this::mapToProvinciaResponse).toList();
    }

    public List<DistritoResponse> getDistritos() {
        return distritoRepository.findAll().stream().map(this::mapToDistritoResponse).toList();
    }
    private DocumentoResponse mapToDocumentoResponse(Documento documento){
        return new DocumentoResponse(
                documento.getId(),
                documento.getNombre()
        );
    }
    private DepartamentoResponse mapToDepartamentoResponse(Departamento departamento){
        return new DepartamentoResponse(
                departamento.getId(),
                departamento.getNombre()
        );
    }
    private ProvinciaResponse mapToProvinciaResponse(Provincia provincia){
        return new ProvinciaResponse(
                provincia.getId(),
                provincia.getNombre(),
                provincia.getDepartamento().getId()
        );
    }
    private DistritoResponse mapToDistritoResponse(Distrito distrito){
        return new DistritoResponse(
                distrito.getId(),
                distrito.getNombre(),
                distrito.getProvincia().getId()
        );
    }



}