package com.patterns.patterns.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patterns.patterns.exception.RequestValidationException;
import com.patterns.patterns.service.CheckValidationMathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.patterns.model.MathModel;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RequestToMathCalculationControllerTest {

    @Mock
    private CheckValidationMathService service;

    @InjectMocks
    private RequestToMathCalculationController controller;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mapper = new ObjectMapper();
    }

    @Test
    void requestCalcRequestToAnotherMicroserviceForCalculationTest() throws Exception {
        String jsonMathModel = mapper.writeValueAsString(getMathModel());
//        doReturn("25", HttpStatus.OK).when(service).process(jsonMathModel);
        when(service.process(jsonMathModel)).thenReturn("25");
        mockMvc.perform(post("/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMathModel))
                .andExpect(status().isOk());
        verify(service, times(1)).process(jsonMathModel);
    }

    @Test
    void getErrorRequestValidationExceptionTest() throws JsonProcessingException {
        String jsonMathModel = mapper.writeValueAsString(getMathModel());
        when(service.process(jsonMathModel)).thenThrow(new RequestValidationException(jsonMathModel + " json is incorrect"));
    }

    @Test
    void getNotArgumentError() {
    }

    private MathModel getMathModel() {
        MathModel model = new MathModel();
        model.setDigitOne(5);
        model.setDigitTwo(5);
        model.setAction("multiply");
        return model;
    }
}