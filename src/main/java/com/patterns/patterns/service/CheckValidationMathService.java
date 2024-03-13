package com.patterns.patterns.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.patterns.patterns.exception.RequestValidationException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;
import ru.patterns.model.MathModel;

@Service
@AllArgsConstructor
@Slf4j
public class CheckValidationMathService {

    private final WebClient webClient;

    private final ObjectMapper myObjMapper;

    public String process(String message) {
        if (validateRequest(message)) {
            return webClient.post()
                    .uri("/calc")
                    .bodyValue(message)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }
        return "";
    }

    @SneakyThrows
    private boolean validateRequest(String message) {
        try {
            MathModel model = myObjMapper.readValue(message, MathModel.class);
            if (!ObjectUtils.isEmpty(model.getDigitOne()) && !ObjectUtils.isEmpty(model.getDigitTwo())) {
                return true;
            }
        } catch (UnrecognizedPropertyException exe) {
            throw new RequestValidationException(String.format("Введенное поле '%s' не соответствует запрашиваемой модели!", exe.getPropertyName()));
        }
        catch (Exception e) {
            throw new RequestValidationException(e.getMessage());
        }
        return false;
    }
}
