package com.ganzouri.books.entity;

public class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private int rate;

    public Book(int id, String title, String author, String category, int rate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.rate = rate;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getRate() {
        return rate;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", rate=" + rate +
                '}';
    }

}