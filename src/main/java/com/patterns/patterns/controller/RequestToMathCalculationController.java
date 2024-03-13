package com.patterns.patterns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patterns.patterns.exception.RequestValidationException;
import com.patterns.patterns.model.MathErrorModel;
import com.patterns.patterns.service.CheckValidationMathService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.patterns.api.RequestToMathCalculationApi;
import ru.patterns.model.MathModel;

@RestController
@AllArgsConstructor
@Slf4j
public class RequestToMathCalculationController implements RequestToMathCalculationApi {

    private final CheckValidationMathService service;

    private final ObjectMapper myObjMapper;

    @SneakyThrows
    @Override
    public ResponseEntity<String> requestCalc(MathModel mathModel) {
        return new ResponseEntity<>(service.process(myObjMapper.writeValueAsString(mathModel)), HttpStatus.OK);
    }

    @SneakyThrows
    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<String> getError(RequestValidationException e) {
        MathErrorModel errorModel = new MathErrorModel();
        errorModel.setError(e.getMessage());
        String str = myObjMapper.writeValueAsString(errorModel);
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @SneakyThrows
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> getNotArgumentError(MethodArgumentNotValidException e) {
        MathErrorModel errorModel = new MathErrorModel();
        errorModel.setError(e.getBindingResult().getFieldError().getDefaultMessage());
        String str = myObjMapper.writeValueAsString(errorModel);
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

}
