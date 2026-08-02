package Lesson_5;

import java.util.Arrays;
import java.util.Scanner;

public class CatchException {
    public static void main(String[] args) {
        System.out.println("Введите длину массива:");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i-1] + 1;
        }
        System.out.println("Введите индекс элемента массива:");
        int index = scan.nextInt();
        int a = 0;
        int j = index - 1;
        try {
            a = arr[j];
            System.out.println(index + " элемент массива: " + a);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Вы вышли за границы массива.");
        } finally {
            scan.close();
        }
        //System.out.println(Arrays.toString(arr));
    }
}
