package com.pb.marenychenko.hw2;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите число №1: ");
        int operand1 = in.nextInt();

        System.out.println("Введите число №2: ");
        int operand2 = in.nextInt();
        in.nextLine();
        boolean inputok;
        int result = 0;

        do {
            System.out.println("Введите оператор (+, -, *, /): ");
            String sign = in.nextLine();
            inputok = true;
            switch (sign) {

                case ("+"):
                    result = operand1 + operand2;
                    break;
                case ("-"):
                    result = operand1 - operand2;
                    break;
                case ("*"):
                    result = operand1 * operand2;
                    break;
                case ("/"):
                    if (operand2 == 0) {
                        System.out.println("Делить на ноль нельзя! Попробуйте другую операцию");
                        inputok = false;
                    }
                    else {
                        result = operand1 / operand2;
                    }
                    break;
                default:{
                    System.out.println("Неверный оператор ");
                    inputok = false;}
            }
        }
        while (!inputok);
        System.out.println("Результат операции: ");
        System.out.print(result);


    }
}
