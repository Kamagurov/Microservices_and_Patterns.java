package com.patterns.patterns.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MathSchedulerService {

    private final ObjectMapper objectMapper;

    private final WebClient webClient;

    private final CheckValidationMathService service;

    @SneakyThrows
//    @Scheduled(fixedDelayString = "${spring.integration.poller.fixed-delay}")
    public void mathProcess() {
        log.info("Try to get model from CreatingModelController");
        String model = webClient.get()
                .uri("create")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        Optional.ofNullable(model).ifPresent(log::info);
        String resultProcess = service.process(model);
        Optional.ofNullable(resultProcess).ifPresent(log::info);
    }
}
