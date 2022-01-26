package br.com.api.stock.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.stock.dto.MovementDTO;
import br.com.api.stock.exception.ApplicationException;
import br.com.api.stock.service.MovementService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/movements", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovementController {

    @NonNull
    private final MovementService movementService;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@Valid @RequestBody final MovementDTO movementDTO) {
        try {
            return new ResponseEntity<>(movementService.save(movementDTO), HttpStatus.OK);
        } catch (ApplicationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
}
