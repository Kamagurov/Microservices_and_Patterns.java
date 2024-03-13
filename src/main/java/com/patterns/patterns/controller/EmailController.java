package com.patterns.patterns.controller;

import com.patterns.patterns.email_Strategy.CommonEmailHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.patterns.api.UserDataApi;
import ru.patterns.model.EmailModel;

@RestController
@AllArgsConstructor
public class EmailController implements UserDataApi {

    private final CommonEmailHandler handler;

    @SneakyThrows
    @Override
    public ResponseEntity<EmailModel> process(String message) {
        return new ResponseEntity<>(handler.process(message), HttpStatus.OK);
    }


//    @PostMapping(path = "message",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public EmailModel process(@RequestBody String message) throws JsonProcessingException {
//        return handler.process(message);
//    }


}
