package com.ceiba.biblioteca.domain.service.internal;

import com.ceiba.biblioteca.domain.model.type.TipoUsuario;

@FunctionalInterface
public interface FindDiasPorTipoUsuario {

    Integer get(TipoUsuario tipoUsuario);

}
