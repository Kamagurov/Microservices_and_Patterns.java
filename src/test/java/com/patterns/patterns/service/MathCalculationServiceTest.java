package com.patterns.patterns.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patterns.patterns.exception.NotValidActionException;
import com.patterns.patterns.math_chainOfResponsibility.Decrement;
import com.patterns.patterns.math_chainOfResponsibility.Increment;
import com.patterns.patterns.math_chainOfResponsibility.Multiply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MathCalculationServiceTest {

    @Spy
    ObjectMapper mapper;

    MathCalculationService service;

    @BeforeEach
    public void init() {
        service = new MathCalculationService(List.of(new Decrement(), new Increment(), new Multiply()), mapper);
    }

    @Test
    void calculateIncrementTest() {
        String response = service.calculate("{\n" +
                "    \"a\": 5,\n" +
                "    \"b\": 4,\n" +
                "    \"action\": \"increment\"\n" +
                "}");
        assertEquals("9", response);
    }

    @Test
    void calculateDecrementTest() {
        String response = service.calculate("{\n" +
                "    \"a\": 5,\n" +
                "    \"b\": 4,\n" +
                "    \"action\": \"decrement\"\n" +
                "}");
        assertEquals("1", response);
    }

    @Test
    void calculateMultiplyTest() {
        String response = service.calculate("{\n" +
                "    \"a\": 5,\n" +
                "    \"b\": 4,\n" +
                "    \"action\": \"multiply\"\n" +
                "}");
        assertEquals("20", response);
    }

    @Test
    void calculateWrongActionTest() {
        assertThrows(NotValidActionException.class, () -> service.calculate("{\n" +
                "    \"a\": 5,\n" +
                "    \"b\": 4,\n" +
                "    \"action\": \"tiply\"\n" +
                "}"));
    }

}