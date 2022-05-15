package com.ceiba.biblioteca.infrastructure.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PrestamoResponse {

    private Integer id;
    private String fechaMaximaDevolucion;

}
