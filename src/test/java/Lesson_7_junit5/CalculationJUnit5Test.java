package Lesson_7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static Lesson_7.Calculation.calculation;


class CalculationJUnit5Test {

    @ParameterizedTest(name = "{0} {1} {2} = {3}")
    @CsvSource({
            "22, +, 16, 38",
            "3, -, 12, -9",
            "55, *, 3, 165",
            "110, /, 2, 55",
            "37, /, -1, -37"
    })
    void calculation_shouldReturnCorrectResult(int a, String sign, int b, int expected) {
        assertEquals(expected, calculation(a, sign, b));
    }

    @Test
    void calculation_shouldThrowException_withMessage_divideBy0() {
        Exception exception = assertThrows(ArithmeticException.class,
                () -> calculation(5, "/", 0));
        assertEquals("Делить на ноль нельзя.", exception.getMessage());
    }

    @ParameterizedTest(name = "calculation({0}, \"{1}\", {2}) → исключение")
    @CsvSource({
            "22, f, 16, 38",
            "3, --, 12, -9",
            "55, ), 3, 165",
            "110, =, 2, 55"
    })
    void calculation_shouldThrowException_forInvalidSign(int a, String sign, int b) {
        Exception exception = assertThrows(ArithmeticException.class,
                () -> calculation(a, sign, b));
        assertEquals("Вы ввели некорректный знак.", exception.getMessage());
    }
}