package com.example.T3.models.dto;

import lombok.Builder;

@Builder
public record DocumentoRequest(
        String nombre
) { }