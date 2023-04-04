package com.example.library.service;
import com.example.library.model.Book;
import java.util.List;
public interface BookService {

    boolean addBook(Book book);
    List<Book> searchBooks(Book book);
    void deleteBook(int bookId);
}

