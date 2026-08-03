package Lesson_10;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

public class TestStepsSimple {

    @Step("Шаг 1: открыть")
    public void open() {
        System.out.println("Открыто");
    }

    @Step("Шаг 2: закрыть")
    public void close() {
        System.out.println("Закрыто");
    }

    @Step("Шаг 3: проверить")
    public void check() {
        System.out.println("Проверено");
    }

    @Test
    public void testSteps() {
        open();
        close();
        check();
    }
}