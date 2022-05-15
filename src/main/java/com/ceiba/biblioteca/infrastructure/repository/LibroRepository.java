package com.ceiba.biblioteca.infrastructure.repository;

import com.ceiba.biblioteca.domain.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro,String> {

    Libro findLibroByIsbn(String identificador);

}
