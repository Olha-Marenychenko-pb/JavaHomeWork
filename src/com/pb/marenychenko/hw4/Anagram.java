package com.pb.marenychenko.hw4;
import java.util.Arrays;
import java.util.Scanner;

public class Anagram {
    static boolean compare_string(String mystring1, String mystring2)
    {
        char[] arr1 = mystring1.toCharArray();
        char[] arr2 = mystring2.toCharArray();
        boolean status = false;
        if (arr1.length == arr2.length)
        {
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            status = Arrays.equals(arr1, arr2);
        }
        return status;
    }

    static String string_clear(String mystring)
    {
        return mystring.replaceAll("[^A-Za-zА-Яа-я]+", "").toLowerCase();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку №1:");
        String str1 =  in.nextLine();
        System.out.println("Введите строку №2:");
        String str2 =  in.nextLine();

        String s1 = string_clear(str1);
        String s2 = string_clear(str2);

        boolean status = compare_string(s1, s2);

        if (status)
            {
                System.out.println("Строки являются анаграммой");
            }
        else
        {
            System.out.println("Строки не являются анаграммой");
        }

    }
}
