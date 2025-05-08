package com.ganzouri.books.controller;

import com.ganzouri.books.entity.Book;
import com.ganzouri.books.request.BookRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/books")
    public List<Book> getAllBooks(@Valid @RequestParam(required = false) String category) {
         return books.stream().filter(book -> category == null || book.getCategory().equalsIgnoreCase(category)).toList();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/books")
    public void addBook(@Valid @RequestBody BookRequest bookRequest) {
        int id = books.isEmpty() ? 1 : books.get(books.size() - 1).getId() + 1;
        books.add(BookRequest.ConvertToBook(id, bookRequest));
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/books/{id}")
    public void updateBook(@PathVariable int id,@Valid @RequestBody BookRequest bookRequest) {

        books.stream().filter(b -> b.getId() == id).findFirst().ifPresent(b -> {
            b.setTitle(bookRequest.getTitle());
            b.setAuthor(bookRequest.getAuthor());
            b.setCategory(bookRequest.getCategory());
            b.setRate(bookRequest.getRate());
        });
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable int id) {
        boolean exists = books.stream().anyMatch(b -> b.getId() == id);
        if (exists) {
            books.removeIf(b -> b.getId() == id);
        } else {
            System.out.println("Book not found");
        }
    }
}
