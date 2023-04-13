package com.example.library.service;
import com.example.library.model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface BookService {

    boolean addBook(Book book);
    List<Book> searchBooks(Book book);
    void deleteBook(int bookId);
    int updateStatus(@Param("bookId") int bookId, @Param("status") int status);
}

