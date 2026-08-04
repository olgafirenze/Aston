package Lesson_4.Figures;


public abstract class Figure implements Calculation {
    private String lineColor;
    private String innerColor;
    private double a;
    private double b;
    private double c;
    private double p;
    private String figureType;

    public Figure(String lineColor, String innerColor) {
        this.lineColor = lineColor;
        this.innerColor = innerColor;
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

}

