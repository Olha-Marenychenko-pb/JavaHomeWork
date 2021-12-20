package com.pb.marenychenko.hw12;

import java.io.*;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.*;

import java.time.LocalDate;
import java.util.stream.IntStream;


public class PhoneBookV2 {
    private ArrayList<Person> persons = new ArrayList<>();
    private String dbFileName = "phone.db";

    public void clearRecords() {
        persons.clear();
        System.out.println();
        System.out.println("--- Все записи удалены ---");
    }

    public int removeRecordByIndex(int removeInd) {
        int result;
        if ((removeInd>=0) && (persons.size()>removeInd)){
            persons.remove(removeInd);
            System.out.println("--- Запись удалена ---");
            result = 1;
        }
        else{
            System.out.println("--- Записи по индексу: " + removeInd + " не существует ---");
            result = -1;
        }
        return result;
    }

    @FunctionalInterface
    public interface PrintRecordsByIndexArray {
        void print(int[] indArray);
    }
    public int searchRecords2(int searchMenuChoice, String searchStr) {
        int[] foundListIndex;
        int recordsFound=0;

        PrintRecordsByIndexArray printRecord= (indArray) -> {
            for(int ind : indArray) {
                System.out.println("Запись " + ind);
                System.out.print(persons.get(ind));
            }
            System.out.println("--- Записей найдено: " + indArray.length + " ---");
        };

        switch (searchMenuChoice) {
            case 1:
                foundListIndex =
                    IntStream.range(0, persons.size())
                            .filter(userInd -> persons.get(userInd).getFio().contains(searchStr)).toArray();
                break;
            case 2:
                foundListIndex =
                        IntStream.range(0, persons.size())
                                .filter(userInd -> persons.get(userInd).getPhones().
                                        stream().filter(x -> x!=null).anyMatch(s->s.contains(searchStr))).toArray();
                break;
            default:
                foundListIndex =
                        IntStream.range(0, persons.size()).filter(userInd -> persons.get(userInd).getAddress()!=null)
                                .filter(userInd -> persons.get(userInd).getAddress().contains(searchStr)).toArray();

        }
        recordsFound = foundListIndex.length;
        printRecord.print(foundListIndex);
        return recordsFound;
    }

    public int getNumberOfRecords(){
        return persons.size();
    };
    public void saveToFile(String fileName) {
        String fName="";
        if(!(fileName != null && !fileName.isEmpty())) {
            fName=dbFileName;
        }
        else {fName=fileName;}

        try{
        File file = Paths.get(fName).toFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(persons);
        objectOutputStream.close();
        System.out.println("--- Справочник сохранен в файл: '" + fName+ "' ---");

        }
        catch( Exception e){
            System.out.println("--- Не удалось сохранить Справочник в файл: '" + fName+ "' ---");
        }

    }

    public void loadFromFile(String fileName) {
        String fName="";
        if(!(fileName != null && !fileName.isEmpty())) {
            fName=dbFileName;
        }
        else {fName=fileName;}

        try{
        File file = Paths.get(fName).toFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        persons = (ArrayList<Person>) objectInputStream.readObject();
        System.out.println("--- Справочник [" + persons.size() +" записей] загружен из файла: '" + fName+ "' ---");

        }
        catch( Exception e){
            System.out.println("--- Не удалось загрузить Справочник из файла: '" + fName+ "' ---");
        }

    }

    static class PersonFioComparator implements Comparator<Person> {
        public int compare(Person a, Person b) {
            return a.getFio().compareTo(b.getFio());
        }
    }
    static class PersonDateOfBirthComparator implements Comparator<Person> {
        public int compare(Person a, Person b) {
            return a.getDateOfBirth().compareTo(b.getDateOfBirth());
        }
    }
    static class PersonRecordTimeComparator implements Comparator<Person> {
        public int compare(Person a, Person b) {
            return a.getRecordChangeTime().compareTo(b.getRecordChangeTime());
        }
    }

    public PhoneBookV2() {
    }

    public void viewRecords2() {
        System.out.println();
        int cnt = (int)IntStream.range(0, persons.size()).peek(i->{System.out.println("Запись: " + i);
            System.out.print(persons.get(i));}).count();
        if (cnt == 0){
            System.out.println("--- В справочнике нет ни одной записи ---");
        }
        else
        {
            System.out.println("--- В справочнике " + cnt + " записей ---");
        }
    }
    public int viewRecordByIndex(int index) {
        int result;
        if ((index>=0) && (persons.size()>index)){
        System.out.println("Запись: " + index);
        System.out.println(persons.get(index));
        result = 1;
        }
        else{
            System.out.println("--- Записи по индексу: " + index + " не существует ---");
            result = -1;
        }
        return result;
    }

    public void sortRecords(int compChoice){
        Comparator<Person> personFioComparator = new PersonFioComparator().thenComparing(new PersonDateOfBirthComparator());
        PersonRecordTimeComparator personRecordTimeComparator = new PersonRecordTimeComparator();

        switch (compChoice){
            case 1:
                Collections.sort(persons, personFioComparator);
                    break;
            case 2:
                Collections.sort(persons, personFioComparator.reversed());
                break;
            case 3:
                Collections.sort(persons, personRecordTimeComparator);
                break;
            case 4:
                Collections.sort(persons, personRecordTimeComparator.reversed());
                break;
        }
    }

    public String getDbFileName() {
        return dbFileName;
    }
    public void setDbFileName(String dbFileName) {
        this.dbFileName = dbFileName;
    }

    public int editRecordByIndex(int editedInd) {
        Person person;
        int successOperation = 0;
        LocalDate drDate = null;
        String addrStr = "";
        String fioStr = "";
        String phoneRegex = "^[0-9()\\- +]+$";
        boolean phoneLoop = false;
        boolean additionalDr = false;
        boolean additionalAddr = false;
        String ChoiceStr = "";

        LinkedHashSet<String>phonesList = new LinkedHashSet<>();

        Scanner in = new Scanner(System.in);
        Person editedPerson = persons.get(editedInd);

        System.out.println("ФИО: " + editedPerson.getFio());
        System.out.println("Изменить ФИО Y/N [Default]; (Выход - 0)");
        ChoiceStr = in.nextLine();
        switch (ChoiceStr.trim().toLowerCase())
        {
            case "y":
            case "yes":
                do {
                    System.out.println("Введите ФИО: ");
                    fioStr = in.nextLine();
                    fioStr = fioStr.trim();
                }
                while(!(fioStr != null && !fioStr.isEmpty()));
                break;
            case "0": return -1;
            case "n":
            case "no":
            default:
                fioStr = editedPerson.getFio();
                break;
        }

        System.out.println("Телефоны: " + editedPerson.getPhones());
        System.out.println("Изменить телефоны ? Y/N [Default]; (Выход - 0)");
        ChoiceStr = in.nextLine();
        switch (ChoiceStr.trim().toLowerCase())
        {
            case "y":
            case "yes":
                do {
                    System.out.println("Введите номера телефонов(,): ");
                    String phonesStr = in.nextLine();
                    String[] phonesArray = phonesStr.split(",");
                    for (String element : phonesArray) {
                        if (element.trim().matches(phoneRegex))
                            phonesList.add(element.trim());
                    }
                    System.out.println("Введены валидные телефоны: " + phonesList);
                    if (phonesList.size()<=0){
                        System.out.println("Не введено ни одного телефона");
                    }
                    System.out.println("Повторить ввод телефонов ? Y/N [Default]; (Выход - 0)");

                    String phoneLoopStr = in.nextLine();
                    switch (phoneLoopStr.trim().toLowerCase())
                    {
                        case "y":
                        case "yes": phoneLoop = true;
                            phonesList.clear();
                            break;
                        case "n":
                        case "no":
                        default:
                            phoneLoop = false;
                            break;
                        case "0": return -1;
                    }

                } while (phoneLoop);
                break;
            case "0": return -1;
            case "n":
            case "no":
            default:
                phonesList = editedPerson.getPhones();
                break;
        }

        do {
            System.out.println("Дата рождения: " + editedPerson.getDateOfBirth());
            System.out.println("Изменить дату рождения ? Y/N [Default]; (Выход - 0)");
            String additionalInfoStr = in.nextLine();

            switch (additionalInfoStr.trim().toLowerCase()) {
                case "y":
                case "yes":
                    additionalDr = true;
                    break;
                case "n":
                case "no":
                default:
                    drDate = editedPerson.getDateOfBirth();
                    additionalDr = false;
                    break;
                case "0":
                    return -1;
            }
            if (additionalDr) {
                System.out.println("Введите Дату рождения(yyyy-MM-dd): ");
                String drStr = in.nextLine();
                try {
                    drDate = LocalDate.parse(drStr);
                    additionalDr = false;

                } catch (DateTimeParseException e) {
                    System.out.println("Неправильный формат даты!");
                    additionalDr = true;
                }

            }
        }while (additionalDr);

        do {
            System.out.println("Адрес: " + editedPerson.getAddress());
            System.out.println("Изменить адрес ? Y/N [Default]; (Выход - 0)");
            String additionalInfoStr = in.nextLine();

            switch (additionalInfoStr.trim().toLowerCase()) {
                case "y":
                case "yes":
                    additionalAddr = true;
                    break;
                case "n":
                case "no":
                default:
                    addrStr = editedPerson.getAddress();
                    additionalAddr = false;
                    break;
                case "0":
                    return -1;
            }
            if (additionalAddr) {
                System.out.println("Введите Адрес: ");
                addrStr = in.nextLine();
                additionalAddr = false;
            }
        }while (additionalAddr);

        if((addrStr != null && !addrStr.isEmpty()) && (drDate != null)) {
            person = new Person(fioStr,phonesList,drDate,addrStr);
        }
        else if (drDate != null)
        {
            person = new Person(fioStr,phonesList,drDate);
        }
        else if (addrStr != null && !addrStr.isEmpty())
        {
            person = new Person(fioStr,phonesList,addrStr);
        }
        else{
            person = new Person(fioStr,phonesList);
        }
        int index = persons.indexOf(person);
        if ((index != editedInd) && (index != -1))
        {

            System.out.println("--- Запись о человеке с таким " + fioStr + " и таким ДР [" + drDate + "] уже существует ---");
            System.out.println(persons.get(index));
            successOperation = 0;
        }
        else {
            System.out.println("--- Старая запись ---");
            System.out.println(editedPerson);
            System.out.println("--- Измененная запись ---");
            System.out.println(person);

            System.out.println("Сохранить изменения ? Y/N [Default]; (Повторить? - 0)");
            ChoiceStr = in.nextLine();
            switch (ChoiceStr.trim().toLowerCase())
            {
                case "y":
                case "yes":

                    if(Objects.equals(editedPerson.getFio(), fioStr) &&
                            Objects.equals(editedPerson.getPhones(), phonesList) &&
                            Objects.equals(editedPerson.getDateOfBirth(), drDate) &&
                            Objects.equals(editedPerson.getAddress(), addrStr))
                    {
                        System.out.println("--- Нечего сохранять. Старая запись идентична изменненной ---");
                    }
                    else {
                        persons.set(editedInd, person);
                        System.out.println("--- Запись изменена ---");
                    }

                    break;
                case "n":
                case "no": return -1;

                case "0": return 0;
            }

            successOperation = 1;
        }
        return successOperation;
    }

    public int createRecord(){
        int successOperation = 0;
        Person person;
        LocalDate drDate = null;
        String addrStr = "";
        String fioStr = "";
        String phoneRegex = "^[0-9()\\- +]+$";

        LinkedHashSet<String>phonesList = new LinkedHashSet<>();
        Scanner in = new Scanner(System.in);
        boolean phoneLoop = false;
        boolean additionalDr = false;
        boolean additionalAddr = false;

        System.out.println("Создание новой записи в телефонной книке...");
        do {
            System.out.println("Введите ФИО: ");
            fioStr = in.nextLine();
            fioStr = fioStr.trim();
        }
        while(!(fioStr != null && !fioStr.isEmpty()));

        do {
            System.out.println("Введите номера телефонов(,): ");
            String phonesStr = in.nextLine();
            String[] phonesArray = phonesStr.split(",");
            for (String element : phonesArray) {
                if (element.trim().matches(phoneRegex))
                    phonesList.add(element.trim());
            }
            System.out.println("Введены валидные телефоны: " + phonesList);
            if (phonesList.size()<=0){
                System.out.println("Не введено ни одного телефона");
            }
            System.out.println("Повторить ввод телефонов ? Y/N [Default]; (Выход - 0)");

            String phoneLoopStr = in.nextLine();
            switch (phoneLoopStr.trim().toLowerCase())
            {
                case "y":
                case "yes": phoneLoop = true;
                            phonesList.clear();
                            break;
                case "n":
                case "no":
                default:
                        phoneLoop = false;
                        break;
                case "0": return -1;
            }

        } while (phoneLoop);
        do {
            System.out.println("Ввести дополнительную информацию - Дата рождения? Y/N [Default]; (Выход - 0)");
            String additionalInfoStr = in.nextLine();

            switch (additionalInfoStr.trim().toLowerCase()) {
                case "y":
                case "yes":
                    additionalDr = true;
                    break;
                case "n":
                case "no":
                default:
                    additionalDr = false;
                    break;
                case "0":
                    return -1;
            }
            if (additionalDr) {
                System.out.println("Введите Дату рождения(yyyy-MM-dd): ");
                String drStr = in.nextLine();
                try {
                    drDate = LocalDate.parse(drStr);
                    additionalDr = false;

                } catch (DateTimeParseException e) {
                    System.out.println("Неправильный формат даты!");
                    additionalDr = true;
                }

            }
        }while (additionalDr);

        do {
            System.out.println("Ввести дополнительную информацию - Адрес? Y/N [Default]; (Выход - 0)");
            String additionalInfoStr = in.nextLine();

            switch (additionalInfoStr.trim().toLowerCase()) {
                case "y":
                case "yes":
                    additionalAddr = true;
                    break;
                case "n":
                case "no":
                default:
                    additionalAddr = false;
                    break;
                case "0":
                    return -1;
            }
            if (additionalAddr) {
                System.out.println("Введите Адрес: ");
                 addrStr = in.nextLine();
                additionalAddr = false;
            }
        }while (additionalAddr);

        if((addrStr != null && !addrStr.isEmpty()) && (drDate != null)) {
            person = new Person(fioStr,phonesList,drDate,addrStr);
        }
        else if (drDate != null)
        {
            person = new Person(fioStr,phonesList,drDate);
        }
        else if (addrStr != null && !addrStr.isEmpty())
        {
            person = new Person(fioStr,phonesList,addrStr);
        }
        else{
            person = new Person(fioStr,phonesList);
        }
        int index = persons.indexOf(person);
        if (index != -1)
        {

            System.out.println("--- Запись о человеке с таким " + fioStr + " и таким ДР [" + drDate + "] уже существует ---");
            System.out.println(persons.get(index));
            successOperation = 0;
        }
        else {
            System.out.println("Сохранить запись ? Y[Default]/N; (Повторить? - 0)");
            String ChoiceStr = in.nextLine();
            switch (ChoiceStr.trim().toLowerCase())
            {
                case "n":
                case "no": return -1;

                case "0": return 0;
                case "y":
                case "yes":
                default:
                    persons.add(person);
                    System.out.println("--- Запись добавлена в справочник ---");

                    break;

            }

            successOperation = 1;
        }

    return successOperation;
    }
}
