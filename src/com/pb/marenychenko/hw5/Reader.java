package com.pb.marenychenko.hw5;

public class Reader {
    private static int readerCount = 0;

    private String fullName;
    private String libraryCard;
    private String faculty;
    private String birthDate;
    private String phone;
    public Reader(String fullName, String libraryCard, String faculty, String birthDate, String phone ) {
        this.fullName = fullName;
        this.libraryCard = libraryCard;
        this.faculty = faculty;
        this.birthDate = birthDate;
        this.phone = phone;
        readerCount++;
    }
    public String getInfo()
    {
        return "Читатель " + fullName + " с читательским билетом №: " + libraryCard + " и телефоном: " + phone;
    }
    public void takeBook(int count)
        {
            System.out.println(fullName + " взял " + count + " книги");
        }
    public void takeBook(String... book_title)
    {
        System.out.print(fullName + " взял книги: ");
        System.out.println(String.join(", ", book_title));
    }
    public void takeBook(Book... book_obj)
    {
        System.out.print(fullName + " взял книги: ");

        String [] myarray = new String[book_obj.length];
        int i = 0;
        for (Book Book : book_obj) {
            myarray[i] = Book.getInfo();
            i++;
        }
        System.out.println(String.join(", ", myarray));

    }

    public void returnBook(int count)
    {
        System.out.println(fullName + " вернул " + count + " книги");
    }
    public void returnBook(String... book_title)
    {
        System.out.print(fullName + " вернул книги: ");
        System.out.println(String.join(", ", book_title));
    }
    public void returnBook(Book... book_obj)
    {
        System.out.print(fullName + " вернул книги: ");

        String [] myarray = new String[book_obj.length];
        int i = 0;
        for (Book Book : book_obj) {
            myarray[i] = Book.getInfo();
            i++;
        }
        System.out.println(String.join(", ", myarray));
    }
}
