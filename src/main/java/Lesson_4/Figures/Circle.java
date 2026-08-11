package Lesson_4.Figures;

public class Circle extends Figure {

    public Circle (String lineColor, String innerColor, double a) {
        super(lineColor, innerColor);
        this.a = a;
        this.figureType = "circle";
    }

    @Override
    public double getA() {
        return this.a;
    }

    @Override
    public double getB() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getC() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getFigureType() {
        return this.figureType;
    }

    @Override
    public double getP() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double calcPerimeter() {
        return 2 * Math.PI * getA();
    }

    @Override
    public double calcArea() {
        return Math.PI * getA() * getA();

    }

}