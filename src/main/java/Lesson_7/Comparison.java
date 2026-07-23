package Lesson_7;

public class Comparison {
    static int comp;
    public static int compare (int a, int b) throws IllegalArgumentException {
        if (a > b) return comp = 1;
        if (a < b) return comp = -1;
        return comp = 0;
    }

    public void main(String[] args) {
        compare (-37, -37);
        switch (comp) {
            case 1 -> System.out.println("Первое число больше.");
            case -1 -> System.out.println("Второе число больше.");
            case 0 -> System.out.println("Числа равны.");
        }
    }
}