package com.ceiba.biblioteca.domain.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TipoUsuario {

    USUARIO_AFILIADO(1),
    USUARIO_EMPLEADO_BIBLIOTECA(2),
    USUARIO_INVITADO(3);

    private final Integer value;

    public static TipoUsuario getByValue(Integer value) {
        return Arrays.stream(TipoUsuario.values())
                .filter(item -> value.equals(item.getValue()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Tipo de usuario no permitido en la biblioteca"));
    }
}
