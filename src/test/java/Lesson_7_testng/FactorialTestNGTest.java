ackage Lesson_7;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import java.math.BigInteger;

import static Lesson_7.Factorial.factorial;


public class FactorialTestNGTest {

    @DataProvider ( name = "validData")
    public Object[][] validData() {
        return new Object[][] {
                {0, BigInteger.ONE},
                {1, BigInteger.ONE},
                {4, BigInteger.valueOf(24)},
                {5, BigInteger.valueOf(120)},
                {9, BigInteger.valueOf(362880)},
                {25, new BigInteger("15511210043330985984000000")}
        };
    }

    @Test(dataProvider = "validData", description = "Возвращает верный результат для корректных данных")
    public void factorial_shouldReturnCorrectResult(int n, BigInteger expected) {
        assertEquals(factorial(n), expected);
    }



    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Число должно быть >= 0")
    public void factorial_shouldThrowException() {
        factorial(-7);
    }

}