package com.ceiba.biblioteca.domain.service;

import com.ceiba.biblioteca.domain.model.FilterPrestamo;
import com.ceiba.biblioteca.domain.model.Libro;
import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.domain.model.type.TipoUsuario;
import com.ceiba.biblioteca.domain.service.internal.*;
import com.ceiba.biblioteca.exceptions.InvitadoConMasDeUnPrestamoExcepcion;
import lombok.Setter;

@Setter
public class PrestamoService {

    private CalculoFechaMaximaDevolucion calculoFechaMaximaDevolucion;
    private FindDiasPorTipoUsuario findDiasPorTipoUsuario;
    private SavePrestamo savePrestamo;
    private FindLibro findLibro;
    private FindPrestamoByIdentificacionUsuario findPrestamoByIdentificacionUsuario;
    private ExistPrestamo existPrestamo;
    private FindPrestamoById findPrestamoById;

    public Prestamo getPrestamo(FilterPrestamo filterPrestamo) {
        return prestamo(filterPrestamo);
    }

    public Prestamo findPrestamoById(Integer id) {
        return findPrestamoById.get(id);
    }

    private Prestamo prestamo(FilterPrestamo filterPrestamo) {
        if (!existPrestamo(filterPrestamo)) {
            Integer sumarDias = findDiasPorTipoUsuario.get(TipoUsuario.getByValue(filterPrestamo.getTipoUsuario()));
            String fechaMaximaDevolucion = calculoFechaMaximaDevolucion.get(sumarDias);
            Libro libro = findLibro.get(filterPrestamo.getIdentificadorUnicoLibro());
            Integer idPrestamo = findPrestamoByIdentificacionUsuario.getId(filterPrestamo.getIdentificacionUsuario());
            Prestamo prestamo = getPrestamo(filterPrestamo, libro, fechaMaximaDevolucion);
            prestamo.setId(idPrestamo);
            savePrestamo.execute(prestamo);
            return prestamo;
        }
        throw new InvitadoConMasDeUnPrestamoExcepcion(("El usuario con identificación ")
                .concat(filterPrestamo.getIdentificacionUsuario())
                .concat(" ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo"));
    }

    private boolean existPrestamo(FilterPrestamo filterPrestamo) {
        return existPrestamo.get(filterPrestamo.getIdentificacionUsuario());
    }

    private Prestamo getPrestamo(FilterPrestamo filterPrestamo, Libro libro, String fechaMaximaDevolucion) {
        return Prestamo.builder()
                .fechaMaximaDevolucion(fechaMaximaDevolucion)
                .identificacionLibro(libro.getIsbn())
                .identificacionUsuario(filterPrestamo.getIdentificacionUsuario())
                .tipoUsuario(filterPrestamo.getTipoUsuario())
                .build();
    }


}
