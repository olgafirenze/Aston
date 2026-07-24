package Lesson_7;

import org.testng.annotations.*;

import static Lesson_7.Calculation.calculation;
import static org.testng.Assert.*;

import static Lesson_7.Triangle.area;
import static Lesson_7.Triangle.area;

public class TriangleTestNGTest {

    @DataProvider (name = "type1Data")
    public Object[][] type1Data() {
        return new Object[][] {
                {3, 4, 6},
                {1.5, 2, 1.5},
                {10, 10, 50}
        };
    }

    @Test (dataProvider = "type1Data", description = "По основанию и высоте"
    )
    public void area_byBaseAndHeight(double a, double b, double expected) {
        assertEquals(area(1, a, b), expected);
    }



    @DataProvider (name = "type1InvalidData")
    public Object[][] type1InvalidData() {
        return new Object[][] {
                {2, 4, 18},
                {3, 5, 16},
                {4, 90, 67},
                {7, 1, 11}
        };
    }

    @Test (dataProvider = "type1InvalidData",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Неверный тип треугольника.",
            description = "Неправильно передан тип треугольника по основанию и высоте")
    public void area_byBaseAndHeight_shouldThrowException_forInvalidType(int type, double a, double b) {
        area(type, a, b);
    }





}