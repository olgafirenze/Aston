package Lesson_10;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleAllureTest {

    @Step("Это простой шаг")
    private void simpleStep() {
        System.out.println("Шаг выполнен!");
    }

    @Test
    public void testAllure() {
        simpleStep();
        assertThat(true).isTrue();
    }
}