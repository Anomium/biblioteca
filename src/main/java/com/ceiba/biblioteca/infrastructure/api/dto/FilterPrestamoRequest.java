package com.ceiba.biblioteca.infrastructure.api.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class FilterPrestamoRequest {

    @NotBlank
    @NotNull
    private String isbn;

    @NotBlank
    @Pattern(regexp = "^[1-9](([0-9])+)?$", message = "Cedula no valida. (Permitido: Numerico y que comiencen por numero diferente de cero(0)).")
    private String identificacionUsuario;

    @Size(max = 1)
    @Pattern(regexp = "^(1|2|3)$", message = "Tipo de usuario no permitido en la biblioteca")
    private String tipoUsuario;

}
