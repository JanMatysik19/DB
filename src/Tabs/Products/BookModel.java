package Tabs.Products;

import java.sql.Date;

public class BookModel {
    private int id;
    private String title;
    private String author;
    private int yearOfIssue;

    public BookModel(int id, String title, String author, int yearOfIssue) {
        this.author = author;
        this.yearOfIssue = yearOfIssue;
        this.id = id;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}