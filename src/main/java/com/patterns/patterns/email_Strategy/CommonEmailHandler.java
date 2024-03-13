package com.patterns.patterns.email_Strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.patterns.model.EmailModel;

import java.util.List;

@Component
@AllArgsConstructor
public class CommonEmailHandler {

    private final List<EmailHandler> emailHandlers;

    private final ObjectMapper mapper;

    public EmailModel process(String message) throws JsonProcessingException {
        EmailModel emailModel = mapper.readValue(message, EmailModel.class);
        emailHandlers.forEach(e -> e.process(emailModel));
        return emailModel;
    }
}