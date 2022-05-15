CREATE TABLE LIBROS(
    IDENTIFICADOR VARCHAR(10) UNIQUE,
    NOMBRE VARCHAR(255) NOT NULL,
    ID NUMBER
);

CREATE TABLE PRESTAMOS(
   ID VARCHAR(10) AUTO_INCREMENT,
   IDENTIFICADOR_LIBRO VARCHAR(10),
   FECHA_MAXIMA_DEVOLUCION VARCHAR(20),
   IDENTIFICACION_USUARIO VARCHAR(10) NOT NULL
);

INSERT INTO `LIBROS` (`IDENTIFICADOR`, `NOMBRE`) VALUES ('QWERTY123A', 'HARRY POTTER');
INSERT INTO `LIBROS` (`IDENTIFICADOR`, `NOMBRE`) VALUES ('QWERTY123B', 'HISTORIAS DE CRONOPIOS Y DE FAMAS DE JULIO CORTÁZAR');
INSERT INTO `LIBROS` (`IDENTIFICADOR`, `NOMBRE`) VALUES ('QWERTY123C', 'YO, ROBOT DE ISAAC ASIMOV');
INSERT INTO `LIBROS` (`IDENTIFICADOR`, `NOMBRE`) VALUES ('QWERTY123D', 'CRÓNICA DE UNA MUERTE ANUNCIADA DE GABRIEL GARCÍA MÁRQUEZ');
INSERT INTO `LIBROS` (`IDENTIFICADOR`, `NOMBRE`) VALUES ('QWERTY123E', 'EL RETRATO DE DORIAN GRAY DE OSCAR WILDE');
INSERT INTO `LIBROS` (`IDENTIFICADOR`, `NOMBRE`) VALUES ('EQWQW8545', 'EL RETRATO DE DORIAN GRAY DE OSCAR WILDE');