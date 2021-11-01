package com.pb.marenychenko.hw5;

public class Book {
    private static int bookCount = 0;
    private String title;
    private String author;
    private String year;
    public Book(String title, String author, String year ) {
        this.title = title;
        this.author = author;
        this.year = year;
        bookCount++;
    }
    public String getInfo()
        {
            return title + " (" + author + " " + year + ")";
        }
}
