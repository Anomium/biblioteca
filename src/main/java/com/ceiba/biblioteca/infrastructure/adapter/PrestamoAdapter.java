package com.ceiba.biblioteca.infrastructure.adapter;

import com.ceiba.biblioteca.domain.model.Libro;
import com.ceiba.biblioteca.domain.service.PrestamoService;
import com.ceiba.biblioteca.domain.service.internal.*;
import com.ceiba.biblioteca.infrastructure.repository.LibroRepository;
import com.ceiba.biblioteca.infrastructure.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrestamoAdapter extends PrestamoService {

    public static final int DIAS_AGREGAR_10 = 10;
    public static final int DIAS_AGREGAR_8 = 8;
    public static final int DIAS_AGREGAR_7 = 7;

    private final LibroRepository libroRepository;
    private final PrestamoRepository prestamoRepository;

    public PrestamoAdapter(LibroRepository libroRepository,
                           PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        super.setCalculoFechaMaximaDevolucion(getFechaMaximaDevolucion());
        super.setFindDiasPorTipoUsuario(getDiasPorTipoUsuario());
        super.setFindLibro(getLibro());
        super.setSavePrestamo(savePrestamo());
        super.setFindPrestamoByIdentificacionUsuario(getIdPrestamo());
        super.setExistPrestamo(existPrestamo());
        super.setFindPrestamoById(getPrestamoById());
        save();
    }

    private CalculoFechaMaximaDevolucion getFechaMaximaDevolucion() {
        return sumarDias -> {
            LocalDate fechaDevolucion = LocalDate.now();
            int contadorDias = 0;
            while(contadorDias < sumarDias) {
                fechaDevolucion = fechaDevolucion.plusDays(1);
                if (esDiaDeSemana(fechaDevolucion)) {
                    ++contadorDias;
                }
            }
            return this.formatoFecha(fechaDevolucion);
        };
    }

    private String formatoFecha(LocalDate fechaMaximaDevolucion) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaMaximaDevolucion.format(formato);
    }

    private boolean esDiaDeSemana(LocalDate fecha) {
        return !(fecha.getDayOfWeek() == DayOfWeek.SATURDAY ||
                fecha.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    private FindDiasPorTipoUsuario getDiasPorTipoUsuario() {
        return tipoUsuario -> {
            switch (tipoUsuario){
                case USUARIO_AFILIADO:
                    return DIAS_AGREGAR_10;
                case USUARIO_EMPLEADO_BIBLIOTECA:
                    return DIAS_AGREGAR_8;
                case USUARIO_INVITADO:
                    return DIAS_AGREGAR_7;
            }
            return null;
        };
    }

    private FindLibro getLibro() {
        return libroRepository::findLibroByIsbn;
    }

    @Transactional
    SavePrestamo savePrestamo() {
        return prestamoRepository::save;
    }

    private FindPrestamoByIdentificacionUsuario getIdPrestamo() {
        return prestamoRepository::findByIdentificacionUsuario;
    }

    private ExistPrestamo existPrestamo() {
        return prestamoRepository::existsByAndIdentificacionUsuario;
    }

    private FindPrestamoById getPrestamoById() {
        return id -> prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el prestamo"));
    }

    private void save() {
        List<Libro> libros = new ArrayList<>();
        libros.add(Libro.builder().id(1).isbn("ASDA7884").nombre("HARRY POTTER").build());
        libros.add(Libro.builder().id(2).isbn("AWQ489").nombre("HISTORIAS DE CRONOPIOS Y DE FAMAS DE JULIO CORTÁZAR").build());
        libros.add(Libro.builder().id(3).isbn("EQWQW8545").nombre("YO, ROBOT DE ISAAC ASIMOV").build());
        libroRepository.saveAll(libros);
    }
}
