package Lesson_7;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import java.math.BigInteger;

import static Lesson_7.Factorial.factorial;


public class FactorialTestNGTest {

    @Test(dataProvider = "validData")
    public Object[][] validData() {
        return new Object[][] {
                {0, 1},
                {1, 1},
                {4, 24},
                {5, 120},
                {9, 362880},
                {25, 15511210043330985984000000}
        };
    }

    @Test(dataProvider = "validData")
    public void factorial_shouldReturnCorrectResult(int n, BigInteger expected) {
        assertEquals(factorial(n), BigInteger.valueOf(expected));
    }

}