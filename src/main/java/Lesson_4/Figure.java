package Lesson_4;

interface Calculation {
    public double getA();
    public double getB();
    public double getC();
    public double getP();
    public String getFigureType();
    public default double calcPerimeter() {
        return switch (getFigureType()) {
            case "circle" -> 2 * Math.PI * getA();
            case "rectangle" -> (getA() + getB()) * 2;
            case "triangle" -> (getA() + getB() + getC());
            default -> 0;
        };
    }
    public default double calcArea() {
        return switch (getFigureType()) {
            case "circle" -> Math.PI * getA() * getA();
            case "rectangle" -> getA() * getB();
            case "triangle" -> Math.sqrt(getP() * (getP() - getA()) * (getP() - getB()) * (getP() - getC()));
            default -> 0;
        };
    }
}

public class Figure implements Calculation {
    private String lineColor;
    private String innerColor;
    private double perimeter;
    private double area;
    private double a;
    private double b;
    private double c;
    private double p;
    private String figureType;

    public Figure() {}

    public Figure(String lineColor, String innerColor) {
        this.lineColor = lineColor;
        this.innerColor = innerColor;
    }

    public Figure(String lineColor, String innerColor, double a) {
        this.lineColor = lineColor;
        this.innerColor = innerColor;
        this.a = a;
        this.figureType = "circle";
    }

    public Figure(String lineColor, String innerColor, double a, double b) {
        this.lineColor = lineColor;
        this.innerColor = innerColor;
        this.a = a;
        this.b = b;
        this.figureType = "rectangle";
    }

    public Figure(String lineColor, String innerColor, double a, double b, double c) {
        this.lineColor = lineColor;
        this.innerColor = innerColor;
        this.a = a;
        this.b = b;
        this.c = c;
        this.p = (a + b + c) / 2;
        this.figureType = "triangle";
    }

    @Override
    public double getA() {
        return this.a;
    }

    @Override
    public double getB() {
        return this.b;
    }

    @Override
    public double getC() {
        return this.c;
    }

    @Override
    public String getFigureType() {
        return this.figureType;
    }

    @Override
    public double getP() {
        return this.p;
    }

    public void info() {
        String typeName = switch (this.figureType) {
            case "circle" -> "Круг";
            case "rectangle" -> "Прямоугольник";
            case "triangle" -> "Треугольник";
            default -> "Фигура не определена.";
        };
        System.out.printf("%s\nПериметр: %.2f\nПлощадь: %.2f\nЦвет фона: %s\nЦвет границ: %s\n", typeName,
                calcPerimeter(), calcArea(), lineColor, innerColor);
    }

    public static void main(String[] args) {
        Figure circle1 = new Figure("Зеленый", "Зеленый", 5);
        circle1.info();
        System.out.println();
        Figure rectangle1 = new Figure("Черный", "Желтый", 4, 5);
        rectangle1.info();
        System.out.println();
        Figure triangle1 = new Figure("Фиолетовый", "Белый", 3, 4, 5);
        triangle1.info();
    }
}

