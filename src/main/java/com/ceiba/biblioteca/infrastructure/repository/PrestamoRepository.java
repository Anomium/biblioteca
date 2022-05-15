package com.ceiba.biblioteca.infrastructure.repository;

import com.ceiba.biblioteca.domain.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    @Query(value = "SELECT * FROM PRESTAMOS WHERE IDENTIFICACION_USUARIO = ?1", nativeQuery = true)
    Integer findByIdentificacionUsuario(String identificacionUsuario);

    @Query(value = "SELECT CAST(CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS BIT) " +
            "FROM PRESTAMOS WHERE IDENTIFICACION_USUARIO = ?1 AND TIPO_USUARIO = '3'", nativeQuery = true)
    Boolean existsByAndIdentificacionUsuario(String IdentificacionUsuario);

    Optional<Prestamo> findById(Integer id);

}
