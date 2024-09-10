package com.gestionelarca.system.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FacturaDTO {
    @NotBlank(message="La emision no puede ir vacío")
    private Long emision;
    @NotNull(message="El total no puede ir vacío")
    private Long total;
}