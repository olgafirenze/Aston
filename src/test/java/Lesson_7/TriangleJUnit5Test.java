package Lesson_7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

import static Lesson_7.Triangle.area;

class TriangleJUnit5Test {

    @ParameterizedTest (name = "area(1, {0}, {1}) = {2}")
    @CsvSource({
            "3, 4, 6",
            "5, 12, 30",
            "2.5, 4, 5"
    })
    @DisplayName("По 2 параметрам: по основанию и высоте, корректные значения")
    void area_byBaseAndHeight_shouldReturnCorrectResult(double a, double b, double expected) {
        assertEquals(expected, area(1, a, b), 0.0001);
    }



    @ParameterizedTest (name = "area({0}, {1}, {2}) -> исключение")
    @CsvSource({
            "2, 4, 6",
            "3, 12, 30",
            "4, 4, 5"
    })
    @DisplayName("По 2 параметрам: по основанию и высоте, исключение при неверном выборе типа треугольника")
    void area_byBaseAndHeight_shouldThrowException_withMessage(int type, double a, double b) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> area(type, a, b));
        assertEquals("Неверный тип треугольника.", exception.getMessage());
    }



    @ParameterizedTest (name = "area({0}, {1}, {2}, {3}) = {4}")
    @CsvSource({
            "2, 5, 30, 60, 5.4126",
            "2, 10, 45, 45, 25.0",
            "3, 4, 6, 30, 6.0",
            "4, 13, 14, 15, 84.0",
    })
    @DisplayName("По 3 параметрам(1 сторона и 2 угла, 2 стороны и 1 угол, 3 стороны), " +
            "корректные значения")
    void area_3params_shouldReturnCorrectResult(int type, double a, double b, double c,
                                                        double expected) {
        assertEquals(expected, area (type, a, b, c), 0.0001);
    }



    @ParameterizedTest (name = "area({0}, {1}, {2}, {3}) -> исключение")
    @CsvSource({
            "1, 5, 30, 60",
            "7, 13, 14, 15",
    })
    @DisplayName("По 3 параметрам(1 сторона и 2 угла, 2 стороны и 1 угол, 3 стороны), " +
            "исключение при неверном выборе типа треугольника ")
    void area_3params_shouldThrowException_withMessage(int type, double a, double b, double c) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> area(type, a, b, c));
        assertEquals("Неверный тип треугольника.", exception.getMessage());
    }



    @ParameterizedTest (name = "area(2, {0}, {1}) -> исключение")
    @CsvSource({
            "10, 65, 138",
            "31, 160, 30"
    })
    @DisplayName("По 1 стороне и 2 углам, исключение на несуществующий треугольник")
    void area_by1side2angles_shouldThrowException (double a, double b, double c) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                area(2, a, b, c));
        assertEquals("Такой треугольник не существует.", exception.getMessage());
    }



    @ParameterizedTest (name = "area(4, {0}, {1}) -> исключение")
    @CsvSource({
            "10, 5, 5",
            "3, 6, 3",
            "11, 1, 2"
    })
    @DisplayName("По 3 сторонам, исключение на несуществующий треугольник")
    void area_by3sides_shouldThrowException (double a, double b, double c) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                area(4, a, b, c));
        assertEquals("Такой треугольник не существует.", exception.getMessage());
    }
}