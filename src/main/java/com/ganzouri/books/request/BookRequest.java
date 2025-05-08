package com.ganzouri.books.request;

import com.ganzouri.books.entity.Book;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class BookRequest {
    @Size(min = 1, max = 50)
    private String title;
    @Size(min = 1, max = 30)
    private String author;
    @Size(min = 1, max = 15)
    private String category;
    @Max(value = 10, message = "Rate must be less than or equal to 10")
    @Min(0)
    private int rate;

    public BookRequest( String title, String author, String category, int rate) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rate = rate;
    }

    public static Book ConvertToBook( int id, BookRequest bookRequest) {
        return new Book(id, bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getCategory(), bookRequest.getRate());
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
}
