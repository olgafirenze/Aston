package Lesson_10;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

public class TestMinimal {

    @Step("Минимальный шаг 1")
    public void step1() {
        System.out.println("Step 1");
    }

    @Step("Минимальный шаг 2")
    public void step2() {
        System.out.println("Step 2");
    }

    @Test
    public void testMinimal() {
        step1();
        step2();
    }
}