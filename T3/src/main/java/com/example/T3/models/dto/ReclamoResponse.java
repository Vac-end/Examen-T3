package com.example.T3.models.dto;

import java.util.Date;

public record ReclamoResponse(
        Long id,
        String nombreCompleto,
        String documento,
        Long tipoDocumentoId,
        Long departamentoId,
        Long provinciaId,
        Long distritoId,
        String domicilio,
        String correoElectronico,
        String telefono,
        String celular,
        String fechaHecho,
        String funcionario,
        String descripcion
) {
}
