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



    @DataProvider (name = "type234Data")
    public Object[][] type234Data() {
        return new Object[][] {
                {2, 5, 30, 60, 5.4126},
                {2, 10, 45, 45, 25},
                {3, 4, 6, 30, 6},
                {4, 13, 14, 15, 84.0}
        };
    }

    @Test (dataProvider = "type234Data", description = "По 3 параметрам(1 сторона и 2 угла, " +
            "2 стороны и 1 угол, 3 стороны), корректные значения")
    public void area_by3params_shouldReturnCorrectResult (int type, double a, double b, double c,
                                                          double expected) {
        area(type, a, b, c);
    }



    @DataProvider (name = "type234InvalidType")
    public Object[][] type234InvalidType() {
        return new Object[][] {
            {1, 5, 30, 60},
            {7, 13, 14, 15}
        };
    }

    @Test (dataProvider = "type234InvalidType",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Неверный тип треугольника.",
            description = "По 3 параметрам(1 сторона и 2 угла, " +
            "2 стороны и 1 угол, 3 стороны), исключение при неверном выборе типа треугольника")
    public void area_by3params_shouldThrowException_forInvalidType (int type, double a, double b, double c) {
        area(type, a, b, c);
    }



    @DataProvider (name = "type2InvalidData")
    public Object[][] type2InvalidData () {
        return new Object[][] {
                {10, 65, 138},
                {31, 160, 30}
        };
    }

    @Test (dataProvider = "type2InvalidData",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Такой треугольник не существует.",
            description = "По 1 стороне и 2 углам, исключение на несуществующий треугольник")
    public void area_by1SideAnd2Angles_shouldThrowException (double a, double b, double c) {
        area(2, a, b, c);
    }



    @DataProvider (name = "type4InvalidData")
    public Object[][] type4InvalidData () {
        return new Object[][] {
                {10, 5, 5},
                {3, 6, 3},
                {11, 1, 2}
        };
    }

    @Test (dataProvider = "type4InvalidData",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Такой треугольник не существует.",
            description = "По 3 сторонам, исключение на несуществующий треугольник")
    public void area_by3Sides_shouldThrowException (double a, double b, double c) {
        area(4, a, b, c);
    }

}