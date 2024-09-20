package com.gestionelarca.system.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdditionalServiceDTO {
    @NotBlank(message="El nombre no puede ir vacío")
    private Long name;
    @NotNull(message="El precio no puede ir vacío")
    private Long price;
}
