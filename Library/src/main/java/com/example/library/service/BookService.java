package com.example.library.service;
import com.example.library.model.Book;
import java.util.List;
public interface BookService {

    boolean addBook(Book book);
    List<Book> searchBooks(Book book);
    void deleteBook(int bookId);
    Book getById(int bookId);
    void update(Book book);
     boolean borrowBook(int bookId);
}

