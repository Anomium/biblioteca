package com.ceiba.biblioteca.domain.service.internal;

import com.ceiba.biblioteca.domain.model.Libro;

@FunctionalInterface
public interface FindLibro {

    Libro get(String id);

}
