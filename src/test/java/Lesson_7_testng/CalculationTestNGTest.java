package Lesson_7;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import static Lesson_7.Calculation.calculation;

public class CalculationTestNGTest {

    @DataProvider (name = "validData")
    public Object[][] validData() {
        return new Object[][] {
                {5, "+", 18, 23},
                {27, "-", 16, 11},
                {13, "-", 67, -54},
                {11, "*", 11, 121},
                {150, "/", 15, 10},
        };
    }

    @Test (dataProvider = "validData", description = "Возвращает верный результат для корректных данных")
    public void calculation_shouldReturnCorrectResult(int a, String sign, int b, int expected) {
        assertEquals(calculation(a, sign, b), expected);
    }


    @Test(expectedExceptions = ArithmeticException.class,
            expectedExceptionsMessageRegExp = "Делить на ноль нельзя.")
    public void calculation_exception_DivideByZero() {calculation(99, "/", 0);}


    @DataProvider (name = "invalidData")
    public Object[][] invalidData() {
        return new Object[][] {
                {5, "n", 18},
                {27, "?", 16},
                {13, "%", 67},
                {11, "$", 11}
        };
    }

    @Test (dataProvider = "invalidData",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Вы ввели некорректный знак.",
            description = "Исключение с сообщением на некорректный знак")
    public void calculation_shouldThrowException_forInvalidSign(int a, String sign, int b) {
        calculation(a, sign, b);
    }

}