package com.ceiba.biblioteca.domain.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PRESTAMOS")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "IDENTIFICADOR_LIBRO")
    private String identificacionLibro;

    @Column(name = "FECHA_MAXIMA_DEVOLUCION")
    private String fechaMaximaDevolucion;

    @Column(name = "TIPO_USUARIO")
    private Integer tipoUsuario;

    @Column(name = "IDENTIFICACION_USUARIO")
    private String identificacionUsuario;

}
