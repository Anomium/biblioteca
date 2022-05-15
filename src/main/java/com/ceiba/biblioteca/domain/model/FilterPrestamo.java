package com.ceiba.biblioteca.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterPrestamo {


    private String identificadorUnicoLibro;
    private String identificacionUsuario;
    private Integer tipoUsuario;

}
