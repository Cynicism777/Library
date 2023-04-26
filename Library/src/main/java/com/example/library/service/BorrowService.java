package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Borrow;

import java.util.List;

public interface BorrowService {
    List<Book> searchBooks(Book book);

    Book getById(int bookId);

    boolean borrowBook(int bookId);

    boolean insertBorrowRecord(Borrow borrow);

    List<Borrow> getBorrowedBooks(int userId);

    boolean returnBook(Borrow borrow);

    List<Borrow> getAllBorrowRecords();

}
