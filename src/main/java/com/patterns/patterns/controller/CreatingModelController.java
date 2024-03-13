package com.patterns.patterns.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.patterns.api.CreateApi;
import ru.patterns.model.MathModel;

import java.util.List;
import java.util.Random;

@RestController
@Slf4j
@AllArgsConstructor
public class CreatingModelController implements CreateApi {

    private static final List<String> LIST = List.of("increment", "decrement", "multiply");

    @Override
    public ResponseEntity<MathModel> createModel() {
        log.info("Try to create model");
        return new ResponseEntity<>(createMathModel(), HttpStatus.OK);
    }

    @SneakyThrows
    public MathModel createMathModel() {
        Random random = new Random();
        MathModel model = new MathModel();
        int a = random.nextInt(1, 101);
        int b = random.nextInt(1, 101);
        String action = LIST.get(random.nextInt(0, 3));
        model.setDigitOne(a);
        model.setDigitTwo(b);
        model.setAction(action);
        return model;
    }

}
