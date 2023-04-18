package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.baomidou.mybatisplus.extension.toolkit.Db.updateById;

@Service
public class BorrowServiceImpl implements BorrowService{
    @Autowired
    private BookMapper bookMapper;
    @Override
    public Book getById(int bookId){
        return bookMapper.selectById(bookId);
    }
    @Override
    public boolean borrowBook(int bookId) {
        Book book = getById(bookId);
        if (book != null && book.getStatus() == 1) {
            book.setStatus(0);
            return updateById(book);
        }
        return false;
    }
}
