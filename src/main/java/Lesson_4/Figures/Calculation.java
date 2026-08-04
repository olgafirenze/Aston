package Lesson_4.Figures;

interface Calculation {
    public double getA();
    public double getB();
    public double getC();
    public double getP();
    public String getFigureType();

    public double calcPerimeter();

    public double calcArea();

    /*public default double calcPerimeter() {
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
    }*/


}
