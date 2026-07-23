package Lesson_7;

import java.math.BigInteger;

public class Factorial {
        public static BigInteger factorial(int n) {
        if (n < 0) throw new IllegalArgumentException ("Число должно быть >= 0");
        BigInteger f = BigInteger.ONE;
        for (int i = 2 ; i <= n; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }

    public static void main(String[] args) {
        System.out.println(factorial(10));
    }
}
