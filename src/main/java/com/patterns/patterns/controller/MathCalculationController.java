package com.patterns.patterns.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patterns.patterns.exception.NotValidActionException;
import com.patterns.patterns.model.MathErrorModel;
import com.patterns.patterns.service.MathCalculationService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.patterns.api.CalculateApi;
import ru.patterns.model.MathModel;

@RestController
@AllArgsConstructor
@Slf4j
@Validated
public class MathCalculationController implements CalculateApi {

    private final MathCalculationService mathService;

    private final ObjectMapper mapper;

    @SneakyThrows
    @Override
    public ResponseEntity<String> getCalc(MathModel mathModel) {
        String request = mapper.writeValueAsString(mathModel);
        log.info("Get request from 1 service {}", request);
        return new ResponseEntity<>(mathService.calculate(request), HttpStatus.OK);
    }

    @ExceptionHandler(NotValidActionException.class)
    public ResponseEntity<String> getError(NotValidActionException e) throws JsonProcessingException {
        MathErrorModel errorModel = new MathErrorModel();
        errorModel.setError(e.getMessage());
        String str = mapper.writeValueAsString(errorModel);
        log.error(e.getMessage());
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> getJsonError(JsonProcessingException e) throws JsonProcessingException {
        MathErrorModel errorModel = new MathErrorModel();
        errorModel.setError(e.getMessage());
        String str = mapper.writeValueAsString(errorModel);
        log.error(e.getMessage());
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> getIllegalError(IllegalStateException e) throws JsonProcessingException {
        MathErrorModel errorModel = new MathErrorModel();
        errorModel.setError(e.getMessage());
        String str = mapper.writeValueAsString(errorModel);
        log.error(e.getMessage());
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

}
