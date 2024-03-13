package com.patterns.patterns.email_Strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.patterns.model.EmailModel;

import static org.junit.jupiter.api.Assertions.*;


class CheckPhoneNumberProcessorTest {

    private EmailHandler handler;

    @BeforeEach
    public void init() {
        handler = new CheckPhoneNumberProcessor();
    }

    @Test
    void processIfNumberLessFourDigitTest() {
        EmailModel model = getTestModel(123);
        EmailModel response = handler.process(model);
        assertEquals(model.getPhoneNumber(), response.getPhoneNumber());
    }

    @Test
    void processIfNumberMoreFourDigitTest() {
        EmailModel model = getTestModel(12345);
        EmailModel response = handler.process(model);
        assertEquals(model.getPhoneNumber(), response.getPhoneNumber());
    }

    private EmailModel getTestModel(Integer phoneNumber) {
        EmailModel emailModel = new EmailModel();
        emailModel.setPhoneNumber(phoneNumber);
        return emailModel;
    }
}