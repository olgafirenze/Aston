package Lesson_5;

import java.util.Arrays;

public class Array44 {
    static int a = 4;
    static int b = 4;
    static int sum;

    public static void array44(String[][] arr) throws IllegalArgumentException, MyArraySizeException, MyArrayDataException {
        if (arr == null) throw new IllegalArgumentException("Массив не может быть null.");
        if (arr.length != a)
            throw new MyArraySizeException("Получено " + arr.length + " строк(и). В массиве должно быть 4 строки.");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) throw new IllegalArgumentException("Строка " + (i + 1) + " не может быть null.");
            if (arr[i].length != a) throw new MyArraySizeException("В строке " + (i + 1) + " получено " +
                    arr[i].length + " столбца(ов)). В каждой строке должно быть 4 столбца.");
        }

        sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    sum = 0;
                    throw new MyArrayDataException("В ячейке [" + (i + 1) + "][" + (j + 1) + "] лежит не число.");
                    }
            }
        }
    }

    public static void main (String[] args){
        String[][] arr = new String[][]{{"1", "2", "3", "4"}, {"1", "0", "1", "6"}, {"5", "4", "3", "8"}, {"1", "2", "3", "4"}};
        try {
            Array44.array44(arr);
        } catch (IllegalArgumentException e1) {
            System.out.println(e1.getMessage());
        }
          catch (MyArraySizeException e2) {
            System.out.println(e2.getMessage());
        }
          catch (MyArrayDataException e3) {
            System.out.println(e3.getMessage());
        }
        if (sum > 0) System.out.println(sum);
    }
}
