package com.ceiba.biblioteca.infrastructure.assembler;

import com.ceiba.biblioteca.domain.model.FilterPrestamo;
import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.infrastructure.api.dto.FilterPrestamoRequest;
import com.ceiba.biblioteca.infrastructure.api.dto.PrestamoFilterResponse;
import com.ceiba.biblioteca.infrastructure.api.dto.PrestamoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring")
public interface PrestamoAssembler {

    @Mapping(source = "isbn", target = "identificadorUnicoLibro")
    @Mapping(source = "identificacionUsuario", target = "identificacionUsuario")
    @ValueMapping(source = "tipoUsuario", target = "tipoUsuario")
    FilterPrestamo convertDtoToModel(FilterPrestamoRequest filterPrestamoRequest);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "fechaMaximaDevolucion", target = "fechaMaximaDevolucion")
    PrestamoResponse convertModelToDto(Prestamo prestamo);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "identificacionLibro", target = "isbn")
    @Mapping(source = "identificacionUsuario", target = "identificacionUsuario")
    @Mapping(source = "tipoUsuario", target = "tipoUsuario")
    @Mapping(source = "fechaMaximaDevolucion", target = "fechaMaximaDevolucion")
    PrestamoFilterResponse convertPrestamoModelToDto(Prestamo prestamo);

}
