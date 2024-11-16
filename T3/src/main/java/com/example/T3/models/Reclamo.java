package com.example.T3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Reclamo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCompleto;
    private String documento;
    private String domicilio;
    private String correoElectronico;
    private String telefono;
    private String celular;
    private String fechaHecho;
    private String funcionario;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "documentoId")
    private Documento tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "departamentoID")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "provinciaID")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "distritoID")
    private Distrito distrito;

}
