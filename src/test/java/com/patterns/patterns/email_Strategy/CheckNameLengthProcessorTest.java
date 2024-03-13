package com.patterns.patterns.email_Strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.patterns.model.EmailModel;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CheckNameLengthProcessorTest {

    private EmailHandler handler;

    @BeforeEach
    public void init() {
        handler = new CheckNameLengthProcessor();
    }

    //1 - Если длина поля name < 4, то записываем name через set и добавляем "Он Казбек"
    //1.1 - Создать модель с полем name меньше 4 символов
    //1.2 - Передать созданную модель в метод EmailHandler
    //1.3 - Проверить длинну имени модели
    //2- Если длина поля name > 4, то возвращаем модель без изменений
    @Test
    void processIfNameContainsLessFourCharTest() {
        EmailModel model = getTestModel("Ann");
        EmailModel response = handler.process(model);
        assertEquals(model.getName() + " Он Казбек", response.getName());
    }

    @Test
    void processIfNameContainsMoreFourCharTest() {
        EmailModel model = getTestModel("Annushka");
        EmailModel response = handler.process(getTestModel("Ann"));
        assertEquals(model.getName(), response.getName());
    }

    private EmailModel getTestModel(String name) {
        EmailModel model = new EmailModel();
        model.setName(name);
        return model;
    }
}