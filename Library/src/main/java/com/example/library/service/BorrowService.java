package com.example.library.service;

import com.example.library.model.Book;

import java.util.List;

public interface BorrowService {
    List<Book> searchBooks(Book book);
    Book getById(int bookId);
    boolean borrowBook(int bookId);
}
