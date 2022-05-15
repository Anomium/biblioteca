package com.ceiba.biblioteca.domain.service.internal;

@FunctionalInterface
public interface ExistPrestamo {

    boolean get(String identificacionUsuario);

}
