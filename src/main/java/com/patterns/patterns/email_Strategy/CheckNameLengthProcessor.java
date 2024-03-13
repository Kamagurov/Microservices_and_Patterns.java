package com.patterns.patterns.email_Strategy;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.patterns.model.EmailModel;

@Component
@Order(value = 2)
public class CheckNameLengthProcessor implements EmailHandler {

    @Override
    public EmailModel process(EmailModel model) {
        System.out.println("Phase Two");
        if (model.getName().length() <= 4) {
            model.setName(model.getName() + " Он Казбек");
        }
        return model;
    }
}
