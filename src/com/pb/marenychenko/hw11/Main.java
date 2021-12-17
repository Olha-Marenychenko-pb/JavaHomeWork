package com.pb.marenychenko.hw11;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int mainMenuChoice = -1;
        int viewMenuChoice = -1;
        int mainMenuResult = -1;
        int editRemoveMenuChoice = -1;
        int removeMenuChoice = -1;
        int editMenuChoice = -1;
        int searchMenuChoice = -1;

        PhoneBook phonebook = new PhoneBook();
        Scanner in = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("*** Телефонный справочник ***");
            System.out.println("# Главное меню #");
            System.out.println("1. Добавить новую запись");
            System.out.println("2. Сортировать и отобразить записи..");
            System.out.println("3. Изменить/Удалить записи..");
            System.out.println("4. Поиск записи..");
            System.out.println("5. Сохранить в файл..");
            System.out.println("6. Загрузить из файла..");
            System.out.println("0. Выход");
            System.out.println();
            System.out.print("Выберите пункт меню: ");
            try {
            mainMenuChoice = in.nextInt();
            }
            catch (InputMismatchException ex)
            {
                mainMenuChoice = -1;
            }
            in.nextLine();

            switch (mainMenuChoice)
            {
                case 0: System.out.println("Завершение работы телефонного справочника"); break;
                case 1:
                    do{
                        mainMenuResult = phonebook.createRecord();
                    }
                    while (mainMenuResult==0);
                    break;
                case 2:
                    do{
                        System.out.println();
                        System.out.println("*** Телефонный справочник ***");
                        System.out.println("# Сортировка и отображение записей #");
                        System.out.println("1. по ФИО");
                        System.out.println("2. по ФИО в обратном порядке");
                        System.out.println("3. по Времени изменения");
                        System.out.println("4. по Времени изменения в обратном порядке");
                        System.out.println("0. Назад");
                        System.out.println();
                        System.out.print("Выберите пункт меню: ");
                        try{
                        viewMenuChoice = in.nextInt();
                        }
                        catch (InputMismatchException ex)
                        {
                            viewMenuChoice = -1;
                        }
                        in.nextLine();

                        switch (viewMenuChoice)
                        {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                phonebook.sortRecords(viewMenuChoice);
                                phonebook.viewRecords();

                                break;
                        }

                    }while(viewMenuChoice != 0);
                    break;
                case 3:
                    do{
                        System.out.println();
                        System.out.println("*** Телефонный справочник ***");
                        System.out.println("# Удаление/Изменение записей #");
                        System.out.println("1. Удалить запись..");
                        System.out.println("2. Изменить запись ..");
                        System.out.println("3. Удалить все записи");
                        System.out.println("0. Назад");
                        System.out.println();
                        System.out.print("Выберите пункт меню: ");
                        try {
                            editRemoveMenuChoice = in.nextInt();
                        }
                        catch (InputMismatchException ex)
                        {
                            editRemoveMenuChoice = -1;
                        }
                        in.nextLine();

                        switch (editRemoveMenuChoice)
                        {
                            case 1:

                                do{
                                    System.out.println();
                                    System.out.println("*** Телефонный справочник ***");
                                    System.out.println("# Удаление/Изменение записи #");
                                    System.out.println("# Удаление записи #");
                                    System.out.println("1. Отобразить все записи");
                                    System.out.println("2. Ввести индекс и удалить запись");
                                    System.out.println("0. Назад");
                                    System.out.println();
                                    System.out.print("Выберите пункт меню: ");
                                    try {
                                        removeMenuChoice = in.nextInt();
                                    }
                                    catch (InputMismatchException ex)
                                    {
                                        removeMenuChoice = -1;
                                    }
                                    in.nextLine();

                                    switch (removeMenuChoice)
                                    {
                                        case 1:phonebook.viewRecords(); break;
                                        case 2:
                                            int removeInd = -1;
                                            System.out.print("Введите индекс: ");
                                            try {
                                             removeInd = in.nextInt();}
                                            catch (InputMismatchException ex)
                                            {
                                                removeInd = -1;
                                            }
                                            in.nextLine();

                                            int removeResult = phonebook.viewRecordByIndex(removeInd);
                                            if (removeResult!= -1)
                                                {
                                                    System.out.print("Вы хотите удалить эту запись'? (Y/N): ");
                                                    String deleteChoice = in.nextLine();
                                                    switch (deleteChoice.trim().toLowerCase())
                                                    {
                                                        case "y":
                                                        case "yes":
                                                            phonebook.removeRecordByIndex(removeInd);
                                                            break;
                                                        case "n":
                                                        case "no":
                                                        case "0":
                                                        default: break;
                                                    }

                                                }

                                            break;
                                    }
                                }while(removeMenuChoice != 0);
                                break;

                            case 2:
                                do{
                                    System.out.println();
                                    System.out.println("*** Телефонный справочник ***");
                                    System.out.println("# Удаление/Изменение записи #");
                                    System.out.println("# Изменение записи #");
                                    System.out.println("1. Отобразить все записи");
                                    System.out.println("2. Ввести индекс и изменить запись");
                                    System.out.println("0. Назад");
                                    System.out.println();
                                    System.out.print("Выберите пункт меню: ");
                                    try {
                                        editMenuChoice = in.nextInt();
                                    }
                                    catch (InputMismatchException ex)
                                    {
                                        editMenuChoice = -1;
                                    }
                                    in.nextLine();

                                    switch (editMenuChoice)
                                    {
                                        case 1:phonebook.viewRecords(); break;
                                        case 2:
                                            int editedInd = -1;
                                            System.out.print("Введите индекс: ");
                                            try {
                                                editedInd = in.nextInt();}
                                            catch (InputMismatchException ex)
                                            {
                                                editedInd = -1;
                                            }
                                            in.nextLine();

                                            int editResult = phonebook.viewRecordByIndex(editedInd);
                                            if (editResult!= -1)
                                            {
                                                System.out.print("Вы хотите изменить эту запись'? Y/N [Default]: ");
                                                String editChoice = in.nextLine();
                                                switch (editChoice.trim().toLowerCase())
                                                {
                                                    case "y":
                                                    case "yes":
                                                        do{
                                                        editResult = phonebook.editRecordByIndex(editedInd);}
                                                        while (editResult==0);
                                                        break;
                                                    case "n":
                                                    case "no":
                                                    case "0":
                                                    default: break;
                                                }

                                            }

                                            break;
                                    }
                                }while(editMenuChoice != 0);
                                break;
                            case 3:
                            {
                                System.out.print("Вы хотите удалить все записи? (Y/N): ");
                                String deleteAllchoice = in.nextLine();
                                switch (deleteAllchoice.trim().toLowerCase())
                                {
                                    case "y":
                                    case "yes":
                                        phonebook.clearRecords();
                                        break;
                                    case "n":
                                    case "no":
                                    case "0":
                                    default: break;
                                }

                            }
                                break;
                        }

                    }while(editRemoveMenuChoice != 0);
                    break;
                case 4:
                    do{
                        System.out.println();
                        System.out.println("*** Телефонный справочник ***");
                        System.out.println("# Поиск записей #");
                        System.out.println("1. по ФИО");
                        System.out.println("2. по Телефону");
                        System.out.println("3. по Адресу");
                        System.out.println("0. Назад");
                        System.out.println();
                        System.out.print("Выберите пункт меню: ");
                        try{
                            searchMenuChoice = in.nextInt();
                        }
                        catch (InputMismatchException ex)
                        {
                            searchMenuChoice = -1;
                        }
                        in.nextLine();

                        switch (searchMenuChoice)
                        {
                            case 1:System.out.println("Поиск по ФИО."); break;
                            case 2:System.out.println("Поиск по Телефону.");break;
                            case 3:System.out.println("Поиск по Адресу.");break;
                        }
                        if(searchMenuChoice>0){
                        String searchStr = "";
                        do {
                            System.out.print("Введите строку поиска: ");
                            searchStr = in.nextLine();
                            searchStr = searchStr.trim();
                            }
                        while(!(searchStr != null && !searchStr.isEmpty()));
                        phonebook.searchRecords(searchMenuChoice, searchStr);
                        }

                    }while(searchMenuChoice != 0);
                    break;
                case 5:
                    if (phonebook.getNumberOfRecords()>0)
                    {
                    System.out.println("Введите имя файла для сохранения справочника. " + phonebook.getDbFileName() + " [default]: ");
                    String fileName = in.nextLine();
                    phonebook.saveToFile(fileName);
                    }
                    else {
                        System.out.println("--- Справочник пуст. Нечего сохранять! ---");
                    }
                    break;
                case 6:
                    System.out.println("Введите имя файла для загрузки справочника. " + phonebook.getDbFileName() + " [default]: ");
                    String fileName = in.nextLine();
                    phonebook.loadFromFile(fileName);
                    break;

            }

        } while (mainMenuChoice !=0);

    }
}
