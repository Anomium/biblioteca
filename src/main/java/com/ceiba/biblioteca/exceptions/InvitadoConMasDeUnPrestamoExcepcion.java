package com.ceiba.biblioteca.exceptions;

public class InvitadoConMasDeUnPrestamoExcepcion extends RuntimeException{

    private static final long serialVersionUID = 1583002602935301000L;

    public InvitadoConMasDeUnPrestamoExcepcion(String mensaje) {
        super(mensaje);
    }

}
