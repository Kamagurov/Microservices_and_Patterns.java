package com.patterns.patterns.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patterns.patterns.model.MathErrorModel;
import com.patterns.patterns.service.MathCalculationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.patterns.model.MathModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class MathCalculationControllerTest {

    @Mock
    MathCalculationService mathService;

    @InjectMocks
    private MathCalculationController controller;

    @Spy
    ObjectMapper mapper;

    @Test
    void getCalcReturnValidResponseEntityTest() throws JsonProcessingException {
        String request = mapper.writeValueAsString(getMathModel());

        doReturn("25").when(mathService).calculate(request);

        var response = controller.getCalc(getMathModel());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("25", response.getBody());
    }

    @Test
    void getError() {

    }

    @Test
    void getJsonError() {
    }

    @Test
    void getIllegalError() {
    }

    private MathModel getMathModel() {
        MathModel model = new MathModel();
        model.setDigitOne(5);
        model.setDigitTwo(5);
        model.setAction("multiply");
        return model;
    }

    private String getMathErrorModel() {
        return "";
    }
}