package Lesson_7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static Lesson_7.Comparison.compare;

class ComparisonJUnit5Test {

    @ParameterizedTest(name = "compare({0}, {1}) = {2}")
    @CsvSource({
            "5, 53, -1",
            "96, 52, 1",
            "-17, 38, -1",
            "-18, -54, 1",
            "34, 34, 0"
    })
    void compare_ShouldReturnCorrectResult(int a, int b, int expected) {
        assertEquals(expected, compare(a, b));
    }
}