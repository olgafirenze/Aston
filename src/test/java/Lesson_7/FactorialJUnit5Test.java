package Lesson_7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static Lesson_7.Factorial.factorial;

import java.math.BigInteger;

class FactorialJUnit5Test {

    @Test
    void factorial_shouldReturn1_for0() {
        assertEquals(BigInteger.ONE, factorial(0));
    }

    @Test
    void factorial_shouldReturn1_for1() {
        assertEquals(BigInteger.ONE, factorial(1));
    }

    @Test
    void factorial_shouldReturn362880_for9() {
        assertEquals(BigInteger.valueOf(362880), factorial(9));
    }

    @Test
    void factorial_shouldReturn120_for5() {
        assertEquals(BigInteger.valueOf(120), factorial(5));
    }

    @Test
    void factorial_shouldReturn121645100408832000_for19() {
        assertEquals(BigInteger.valueOf(121645100408832000L), factorial(19));
    }

    @Test
    void factorial_shouldThrowException_withCorrectMessage_forNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> factorial(-1));
        assertEquals("Число должно быть >= 0", exception.getMessage());
    }
}