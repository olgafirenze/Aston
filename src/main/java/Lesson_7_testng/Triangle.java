package Lesson_7;

public class Triangle {
    double a, b, c;
    int type;

    public static double area(int type, double a, double b) throws IllegalArgumentException {
        if (type == 1) return a * b / 2;
        else throw new IllegalArgumentException("Неверный тип треугольника.");
    }

    public static double area(int type, double a, double b, double c) throws IllegalArgumentException{
        return switch (type) {
            case 2 -> {
                if ((b + c) >= 180) throw new IllegalArgumentException("Такой треугольник не существует.");
                b *= Math.PI / 180.0;
                c *= Math.PI / 180.0;
                yield a * a * Math.sin(b) * Math.sin(c) / (2 * Math.sin(b + c));
            }
            case 3 -> {
                c *= Math.PI / 180.0;
                yield a * b * Math.sin(c) / 2;
            }
            case 4 -> {
                double p = (a + b + c) / 2;
                double x = p * (p - a) * (p - b) * (p - c);
                if (x <= 0) throw new IllegalArgumentException("Такой треугольник не существует.");
                yield Math.sqrt(x);
            }
            default -> throw new IllegalArgumentException("Неверный тип треугольника.");
        };
    };

    public static void main(String[] args) {
        try {
            System.out.println(area(3, 4, 6, 30));
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Площадь не может быть посчитана.");
        }
    }
}