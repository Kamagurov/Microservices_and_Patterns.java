package com.patterns.patterns.email_Strategy;


import ru.patterns.model.EmailModel;

public interface EmailHandler {
    EmailModel process(EmailModel model);

}
