package com.patterns.patterns.email_Strategy;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.patterns.model.EmailModel;

@Component
@Order(value = 1)
public class CheckSurnameProcessor implements EmailHandler {

    @Override
    public EmailModel process(EmailModel model) {
        System.out.println("Phase One");
        if (model.getSurname().contains("ов") || model.getSurname().contains("ова")) {
            model.setSurname(model.getSurname() + " этот человек русский");

        }
        return model;
    }
}
