package com.patterns.patterns.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patterns.patterns.exception.NotValidActionException;
import com.patterns.patterns.math_chainOfResponsibility.MathOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.patterns.model.MathModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MathCalculationService {

    private final Map<String, MathOperation> mathOperationMap;
    private final ObjectMapper mapper;

    public MathCalculationService(List<MathOperation> list, ObjectMapper mapper) {
        this.mathOperationMap = list.stream()
                .peek(e -> log.info("Operation '" + e.getAction() + "' was registered"))
                .collect(Collectors.toMap(MathOperation::getAction, Function.identity()));
        this.mapper = mapper;
    }

    @SneakyThrows
    public String calculate(String message) {
        MathModel model = mapRequest(message);
        return Optional.ofNullable(mathOperationMap)
                .map(targetMap -> targetMap.get(model.getAction()))
                .map(targetAction -> targetAction.calculate(model.getDigitOne(), model.getDigitTwo()))
                .map(Object::toString)
                .orElseThrow(() -> new NotValidActionException(model.getAction()));
    }

    @SneakyThrows
    private MathModel mapRequest(String message) {
        try {
            return mapper.readValue(message, MathModel.class);
        } catch (Exception e) {
            log.error("Request map error", e);
            throw e;
        }
    }
}


