package com.ceiba.biblioteca.infrastructure.api;


import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.domain.service.PrestamoService;
import com.ceiba.biblioteca.exceptions.InvitadoConMasDeUnPrestamoExcepcion;
import com.ceiba.biblioteca.infrastructure.api.dto.FilterPrestamoRequest;
import com.ceiba.biblioteca.infrastructure.api.dto.PrestamoFilterResponse;
import com.ceiba.biblioteca.infrastructure.api.dto.PrestamoResponse;
import com.ceiba.biblioteca.infrastructure.assembler.PrestamoAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    private final PrestamoService prestamoService;
    private final PrestamoAssembler prestamoAssembler;

    public PrestamoControlador(PrestamoService prestamoService,
                               PrestamoAssembler prestamoAssembler) {
        this.prestamoService = prestamoService;
        this.prestamoAssembler = prestamoAssembler;
    }

    @PostMapping
    public PrestamoResponse crear(@Valid @RequestBody FilterPrestamoRequest filterPrestamoRequest) {
        Prestamo prestamo = prestamoService.getPrestamo(prestamoAssembler.convertDtoToModel(filterPrestamoRequest));
        return prestamoAssembler.convertModelToDto(prestamo);
    }

    @GetMapping("/{id-prestamo}")
    public PrestamoFilterResponse findPrestamo(@PathVariable("id-prestamo") Integer id) {
        return prestamoAssembler.convertPrestamoModelToDto(prestamoService.findPrestamoById(id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = "mensaje";
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvitadoConMasDeUnPrestamoExcepcion.class)
    public Map<String, String> handleValidationExceptions(InvitadoConMasDeUnPrestamoExcepcion ex) {
        Map<String, String> errors = new HashMap<>();
            String fieldName = "mensaje";
            String errorMessage = ex.getMessage();
            errors.put(fieldName, errorMessage);
        return errors;
    }

}

