package com.pb.marenychenko.hw3;

import java.util.Scanner;
import java.util.Random;

public class Bingo {
    // Циклическая конструкция - while.

    public static void main(String[] args){
        System.out.println("Отгадайте задуманное число от 0 до 100 с пяти попыток.");
        System.out.println("Для выхода из программы введите - Закончить.");

        final int MAX_ATTEMPT = 5; // Допустимое количетсво попыток.
        int attempt = 0;           // Сечтчик попыток.
        Random r = new Random();
        int low = 0;
        int high = 100;
        int number = r.nextInt(high-low) + low;

        // int number = 23;      // Задуманное число.
        Scanner in = new Scanner(System.in);
        boolean guess = false;

        while (attempt < MAX_ATTEMPT) {
            attempt++;
            System.out.println("Попытка " + attempt + ":");
            String valuestr = in.next();

            if (valuestr.equals("Закончить")) {
                guess = true;
                break;
            }
            int valueint = 0;
            try {
                valueint = Integer.parseInt(valuestr);
            }
            catch (NumberFormatException e)
            {
                valueint = 0;
            }

            if (valueint < number) {
                System.out.println(valueint + " меньше загаданного числа");

            }
            else if (valueint > number) {
                System.out.println(valueint + " больше загаданного числа");

            }
            else {
                guess = true;
                System.out.println("Поздравляем, Вы угадали с " + attempt + " попытки!");
                break;
            }

        }
            if (!guess)
                {
                    System.out.println("У вас закончились попытки! а число было: " + number);
                }
            System.out.println("Конец игры!");
        }

    }

