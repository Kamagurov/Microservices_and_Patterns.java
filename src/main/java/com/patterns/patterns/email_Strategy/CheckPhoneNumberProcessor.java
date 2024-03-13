package com.patterns.patterns.email_Strategy;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.patterns.model.EmailModel;

@Component
@Order(value = 3)
public class CheckPhoneNumberProcessor implements EmailHandler {

    @Override
    public EmailModel process(EmailModel model) {
        System.out.println("Phase Three");
        if (String.valueOf(model.getPhoneNumber()).length() <= 4) {
            model.setPhoneNumber(Integer.parseInt(model.getPhoneNumber() + "999"));
        }
        return model;
    }
}
