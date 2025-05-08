package com.ganzouri.books.controller;

import com.ganzouri.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    public ArrayList<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1,"The Great Gatsby", "F. Scott Fitzgerald", "1234567890", 1));
        books.add(new Book(2,"To Kill a Mockingbird", "Harper Lee", "0987654321", 5));
        books.add(new Book(3,"1984", "George Orwell", "1122334455", 7));
    }


    @GetMapping("/books")
    public List<Book> getAllBooks(@RequestParam(required = false) String category) {
         return books.stream().filter(book -> category == null || book.getCategory().equalsIgnoreCase(category)).toList();
    }
    @GetMapping("/books/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
    @PostMapping("/books")
    public void addBook(@RequestBody Book book) {
        boolean exists = books.stream().noneMatch(b -> b.getTitle().equalsIgnoreCase(book.getTitle()));
        if (exists)
            books.add(book);
        else
            System.out.println("Book already exists");

    }
    @PutMapping("/books/{title}")
    public void updateBook(@PathVariable String title, @RequestBody Book book) {
        boolean exists = books.stream().noneMatch(b -> b.getTitle().equalsIgnoreCase(title));
        if (exists) {
            books.removeIf(b -> b.getTitle().equalsIgnoreCase(title));
            books.add(book);
        } else {
            System.out.println("Book not found");
        }
    }
    @DeleteMapping("/books/{title}")
    public void deleteBook(@PathVariable String title) {
        boolean exists = books.stream().noneMatch(b -> b.getTitle().equalsIgnoreCase(title));
        if (exists) {
            books.removeIf(b -> b.getTitle().equalsIgnoreCase(title));
        } else {
            System.out.println("Book not found");
        }
    }
}
