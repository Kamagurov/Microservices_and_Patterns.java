package com.patterns.patterns.email_Strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.patterns.model.EmailModel;

import static org.junit.jupiter.api.Assertions.*;

class CheckSurnameProcessorTest {

    private EmailHandler handler;

    @BeforeEach
    public void init() {
        handler = new CheckSurnameProcessor();
    }

    @Test
    void processIfNameContainsSuffixOV() {
        EmailModel model = getTestModel();
        EmailModel response = handler.process(model);
        assertTrue(model.getSurname().contains("ов") || model.getSurname().contains("ова")
                , response.getSurname());
    }

    private EmailModel getTestModel() {
        EmailModel emailModel = new EmailModel();
        emailModel.setSurname("Иванов");
        return emailModel;
    }
}