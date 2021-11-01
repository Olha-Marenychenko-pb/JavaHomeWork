package com.pb.marenychenko.hw5;

public class Library {
    public static void main(String[] args) {
        Book firstbook = new Book("Head First Java", "Katie Sierra, Bert Bates", "2003");
        Book secondbook = new Book("Java: A Beginner’s Guide", "Herbert Schildt", "2002");
        Book thirdbook = new Book("Core Java", "Cay S. Horstmann, Gary Cornell", "2014");

        Book [] books = {firstbook,secondbook,thirdbook};

        for (Book x : books) {
            System.out.println(x.getInfo());
        }
        Reader reader1 = new Reader("Иванов И.И.", "001", "Финансы", "1996",
                "878734739");
        Reader reader2 = new Reader("Петров А.А.", "002", "История", "1993",
                "546567878");
        Reader reader3 = new Reader("Сидоров О.О", "003", "Химия", "1998",
                "546567678");

        Reader [] readers = {reader1, reader2, reader3};
        for (Reader x : readers) {
            System.out.println(x.getInfo());
        }
        reader1.takeBook(2);
        reader2.takeBook("Head First Java","Core Java");
        reader2.returnBook(firstbook, thirdbook);
        reader3.takeBook(secondbook, thirdbook);
        reader3.returnBook(1);
        reader1.returnBook("Java: A Beginner’s Guide");

    }
}

