import java.util.Arrays;

public class Lesson2 {
    public static void main(String[] args) {

        //printThreeWords();
        //checkSumSign();
        //printColor();
        //compareNumbers();
        //checkSum10to20(5 ,7);
        //isPositive1(456);
        //isPositive2(11);
        //printString("Гав-гав", 6);
        //leapYear(1984);
        //arrayChange0to1();
        //array100();
        //arrayTask12();
        //diagonal(8);
        /*int[] arr14 = arrayTask14(4, 18);
        System.out.println(Arrays.toString(arr14));*/

    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        int a = 9;
        int b = -12;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        int value = 958642;
        if (value <= 0) {
            System.out.println("Красный");
        } else {
            if (value <= 100) {
                System.out.println("Желтый");
            } else {
                System.out.println("Зеленый");
            }
        }
    }

    public static void compareNumbers() {
        int a = 793;
        int b = 8746;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean checkSum10to20(int a, int b) {
        int c = a + b;
        return c >= 10 && c < 20;
    }

    public static void isPositive1(int number) {
        if (number >= 0) System.out.println("Число положительное.");
            else System.out.println("Число отрицательное.");
    }

    public static boolean isPositive2(int number) {
        return number >= 0;
    }

    public static void printString(String str, int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(str);
        }
    }

    public static boolean leapYear(int year) {
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }

    public static void arrayChange0to1() {
        int[] arr = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1 };
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) arr[i] = 0;
            else arr[i] = 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void array100() {
        int arr[] = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void arrayTask12() {
        int arr[] = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) arr[i] *=2;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void diagonal(int n) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i || i+ j == n - 1) arr[i][j] = 1;
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] arrayTask14(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

}
