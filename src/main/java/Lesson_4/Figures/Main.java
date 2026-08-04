package Lesson_4.Figures;

public class Main {
    public static void main(String[] args) {
        Figure circle1 = new Circle("Зеленый", "Зеленый", 5);
        circle1.info();
        System.out.println();
        Figure rectangle1 = new Rectangle("Черный", "Желтый", 4, 5);
        rectangle1.info();
        System.out.println();
        Figure triangle1 = new Triangle("Фиолетовый", "Белый", 3, 4, 5);
        triangle1.info();
    }
}
