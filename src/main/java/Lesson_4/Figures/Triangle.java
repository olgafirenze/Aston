package Lesson_4.Figures;

public class Triangle extends Figure {

    public Triangle (String lineColor, String innerColor, double a, double b, double c) {
        super(lineColor, innerColor);
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

    @Override
    public double calcPerimeter() {
        (getA() + getB() + getC());
    }

    @Override
    public double calcArea() {
        return Math.sqrt(getP() * (getP() - getA()) * (getP() - getB()) * (getP() - getC()));
    }

}
