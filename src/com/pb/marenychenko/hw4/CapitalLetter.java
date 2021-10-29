package com.pb.marenychenko.hw4;
import java.util.Scanner;

public class CapitalLetter {
    static String word_capital(String myword) {
        if(myword != null && !myword.isEmpty())
            return myword.substring(0, 1).toUpperCase() + myword.substring(1);
        else return myword;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите предложение: ");
        String sentence = in.nextLine();

        String[] arr = sentence.split(" ");

        for (int i = 0; i< arr.length; i++) {
            arr[i] =  word_capital(arr[i]);
        }
        System.out.println("Ваше предложение с заглавными буквами:");
        System.out.println(String.join(" ", arr));

    }
}
