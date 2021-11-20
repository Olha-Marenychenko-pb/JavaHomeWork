package com.pb.marenychenko.hw8;

import java.util.Scanner;

public class OnlineShop {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Auth auth1 = new Auth(true);

        int authCounts = 3;
        System.out.println("Добро пожаловать в Онлайн магазин! Для продолжения работы необходимо зарегистрироваться.");
        int i = authCounts;
        do {
            if (i == 0)   {
                System.out.println("Слишком много неудачных попыток.. попробуйте позже");
                break;
            }
            System.out.println("Введите логин (длина 5-20 символов из латинских букв и цифр):");
            String login = in.nextLine();
            System.out.println("Введите пароль( длина > 5  символов из лат. букв, цифр и знака '_'):");
            String password = in.nextLine();
            System.out.println("Подтвердите пароль:");
            String confirmPassword = in.nextLine();
            try {
                auth1.signUp(login, password, confirmPassword);
            }
            catch(WrongLoginException | WrongPasswordException e)
            {
                System.out.println(e.getMessage());
            }
            if (auth1.getSignedUp()){
                System.out.println("Регистрация успешна! Для продолжения работы войдите в Онлайн магазин. ");
            }
            else
            {
                System.out.println("Нарушены требования к логину/паролю. Повторите процедуру регистрации");
            }
            i--;

        }while (!auth1.getSignedUp());
        if (auth1.getSignedUp()){
         i = authCounts;
        do {
            if (i == 0)   {
                System.out.println("Слишком много неудачных попыток.. попробуйте позже");
                break;
            }
            System.out.println("Введите логин:");
            String login = in.nextLine();
            System.out.println("Введите пароль:");
            String password = in.nextLine();
            try {
                auth1.signIn(login, password);
            }
            catch(WrongLoginException e)
            {
                System.out.println(e.getMessage());
            }
            if (auth1.getSignedIn()){
                System.out.println(auth1.getLogin() + " успешно вошли в Онлайн магазин. Успешных покупок... в следующем ДЗ");
                break;
            }
            else
            {
                System.out.println("Логин и пароль не совпадают с зарегестрированными. Повторите попытку!");
            }
            i--;

        }while (!auth1.getSignedIn());

        }
    }
}
