package Lesson_4.Figures;


public abstract class Figure implements Calculation {
    protected String lineColor;
    protected String innerColor;
    protected double a;
    protected double b;
    protected double c;
    protected double p;
    protected String figureType;

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

