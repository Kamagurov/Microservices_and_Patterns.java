package com.patterns.patterns.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.patterns.model.MathModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CreatingModelControllerTest {

    @Mock
    private CreatingModelController controller;

    private MockMvc mockMvc;

    @Spy
    ObjectMapper myObjMapper;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void createMathModelReturnValidResponseEntityTest() throws Exception {
        MathModel model = getMathModel();
        mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.digitOne").value(5))
                .andExpect(jsonPath("$.digitTwo").value(5))
                .andExpect(jsonPath("$.action").value("multiply"));
    }

    @Test
    void createMathModelAndReturnValidMathModelTest() throws JsonProcessingException {
        var message = "{\n" +
                "    \"digitOne\": 5,\n" +
                "    \"digitTwo\": 5,\n" +
                "    \"action\": \"multiply\"\n" +
                "}";
        var response = myObjMapper.readValue(message, MathModel.class);
        assertEquals(getMathModel(), response);
    }

    private MathModel getMathModel() {
        MathModel model = new MathModel();
        model.setDigitOne(5);
        model.setDigitTwo(5);
        model.setAction("multiply");
        return model;
    }
}