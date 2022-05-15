package com.ceiba.biblioteca.domain.model;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "LIBROS")
public class Libro {

    @Id
    @Column(name = "IDENTIFICADOR", nullable = false)
    private String isbn;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ID")
    private Integer id;

}
