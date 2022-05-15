package com.ceiba.biblioteca.domain.service.internal;

import com.ceiba.biblioteca.domain.model.Prestamo;

@FunctionalInterface
public interface FindPrestamoById {

    Prestamo get(Integer id);

}
