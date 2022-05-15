package com.ceiba.biblioteca.domain.service.internal;

import com.ceiba.biblioteca.domain.model.Prestamo;

@FunctionalInterface
public interface SavePrestamo {

    void execute(Prestamo prestamo);

}
