package com.ceiba.biblioteca.domain.service.internal;

@FunctionalInterface
public interface FindPrestamoByIdentificacionUsuario {
    Integer getId(String identificacionUsuario);
}
