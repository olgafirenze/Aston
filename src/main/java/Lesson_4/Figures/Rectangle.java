package Lesson_4.Figures;

public class Rectangle extends Figure {

    public Rectangle (String lineColor, String innerColor, double a, double b) {
        super(lineColor, innerColor)
        this.a = a;
        this.b = b;
        this.figureType = "rectangle";
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
    public String getFigureType() {
        return this.figureType;
    }

    @Override
    public double calcPerimeter() {
        return (getA() + getB()) * 2;
    }

    @Override
    public double calcArea() {
        return getA() * getB();

    }

}
