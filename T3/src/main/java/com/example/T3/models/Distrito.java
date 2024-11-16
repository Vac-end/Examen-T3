package com.example.T3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Distrito")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    private Provincia provincia;
}
