package Lesson_7;

public class Comparison {
    int comp;
    public void compare(int a, int b) {
        if (a > b) System.out.println(a + " > " + b);
        if (a < b) System.out.println(a + " < " + b);
        if (a == b) System.out.println(a + " = " + b);
    }

    public void main(String[] args) {
        compare(155, 155);
    }
}
