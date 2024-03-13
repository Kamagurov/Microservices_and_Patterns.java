package com.patterns.patterns.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patterns.patterns.email_Strategy.CommonEmailHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.patterns.model.EmailModel;
import ru.patterns.model.MathModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {

    @Mock
    private CommonEmailHandler handler;

    @InjectMocks
    private EmailController controller;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mapper = new ObjectMapper();
    }

    @Test
    void processReturnValidResponseEntity() throws Exception {
        EmailModel model = new EmailModel();
        model.setName("Ivan");
        model.setSurname("Vaka-Vaka");
        model.setPhoneNumber(999);
        String message = mapper.writeValueAsString(model);
        when(handler.process(message)).thenReturn(model);
        mockMvc.perform(post("/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(message))
                .andExpect(status().isOk());
        verify(handler, times(1)).process(message);

//
//        doReturn(new ResponseEntity<>(model, HttpStatus.OK)).when(this.controller).process(message);
//        var response = this.controller.process(message);
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(model, response.getBody());
    }
}