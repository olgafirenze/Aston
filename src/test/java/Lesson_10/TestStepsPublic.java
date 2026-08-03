package Lesson_10;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestStepsPublic {

    @Step("Шаг 1: публичный")
    public void stepOne() {
        System.out.println("Step 1");
    }

    @Step("Шаг 2: публичный с параметром {param}")
    public void stepTwo(String param) {
        System.out.println("Step 2: " + param);
    }

    @Test
    public void testSteps() {
        stepOne();
        stepTwo("Hello");
        assertThat(1).isEqualTo(1);
    }
}