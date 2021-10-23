package com.pb.marenychenko.hw3;
import java.util.Scanner;
import java.util.Random;

public class Array {

    public static int bubbleSort(int arr[]) {
        int arrlength = arr.length;
        boolean swap;
        int swapcount = 0;
        for (int i = 0; i < arrlength-1; i++){
            swap = false;
            for (int j = 0; j < arrlength-i-1; j++){
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapcount++;
                    swap = true;
                }
            }
            if (!swap) {break;}
        }
        return swapcount;
    }
    public static void ArrayPrint(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "; ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int[] array = new int[10];
        int x = array.length;
        Scanner in = new Scanner(System.in);

        // Заполнение массива.
        System.out.println("Создание массива из 10 целых чисел:");
/*        Random r = new Random();
        int low = -100;
        int high = 100;*/

        for (int i = 0; i < array.length; i++) {
            System.out.println("Введите " + i + " элемент массива:");
            array[i] = in.nextInt();
            //array[i] = r.nextInt(high-low) + low;
        }

        // Вывод на экран значений элементов массива.
        ArrayPrint(array);

        int summa = 0;
        int positive = 0;
        for (int i = 0; i < array.length; i++) {
            int elem = array[i];
            summa = summa + elem;
            if (elem >=0) {
                positive ++;
            }
        }
        System.out.println("Cумма всех элементов массива: " + summa);
        System.out.println("Количество положительных элементов массива: " + positive);
        int swcount = bubbleSort(array);
        System.out.println("Массив отсортирован за " + swcount + " перестановок:");
        ArrayPrint(array);

    }
}