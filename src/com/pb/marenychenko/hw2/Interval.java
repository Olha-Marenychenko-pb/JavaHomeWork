package com.pb.marenychenko.hw2;
import java.util.Scanner;

//[0 - 14] [15 - 35] [36 - 50] [51 - 100]
public class Interval {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите число: ");
        int mynumber = in.nextInt();
        if ((mynumber > 100)||(mynumber < 0))
            {
                System.out.println("Число " + mynumber + " вне интервала");
            }
        else if ((mynumber <= 100) && (mynumber >= 51))
            {
                System.out.println("Число " + mynumber + " в интервале [51 - 100]");
            }
        else if ((mynumber <= 35) && (mynumber >= 15))
        {
            System.out.println("Число " + mynumber + " в интервале [15 - 35]");
        }
        else if ((mynumber <= 50) && (mynumber >= 36))
        {
            System.out.println("Число " + mynumber + " в интервале [36 - 50]");
        }
        else
        {
            System.out.println("Число " + mynumber + " в интервале [0 - 14]");
        }

    }
}
