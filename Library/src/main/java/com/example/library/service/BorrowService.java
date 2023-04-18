package com.example.library.service;

import com.example.library.model.Book;

public interface BorrowService {
    Book getById(int bookId);
    boolean borrowBook(int bookId);
}
