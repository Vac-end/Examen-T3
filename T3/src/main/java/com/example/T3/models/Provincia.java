package com.example.T3.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Provincia")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    private Departamento departamento;

    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL)
    private List<Distrito> distritos;

}
