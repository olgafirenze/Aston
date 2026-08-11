package Lesson_7;


import org.testng.annotations.*;
import static org.testng.Assert.*;

import static Lesson_7.Comparison.compare;

public class ComparisonTestNGTest {

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][] {
                {5, 73, -1},
                {19, 18, 1},
                {-7, 7, -1},
                {-100, -19, -1},
                {12, 12, 0}
        };
    }

    @Test(dataProvider = "data", description = "Сравнение с корректными значениями"
    )
    public void compare_shouldReturnCorrectResult(int a, int b, int expected) {
        assertEquals(compare(a,b), expected);
    }
}