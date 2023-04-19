package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import static com.baomidou.mybatisplus.extension.toolkit.Db.updateById;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    // 实现添加图书功能，返回boolean值，成功为true
    @Override
    public boolean addBook(Book book) {
        int insert = bookMapper.insert(book);
        return insert > 0;
    }

    // 实现查询图书功能，根据输入的对象的属性值构造查询条件
    @Override
    public List<Book> searchBooks(Book book) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        // book_id 与 year 值相等时添加条件，其余属性值为模糊查询
        if (book.getBookId() != null) {
            wrapper.eq("book_id", book.getBookId());
        }
        if (book.getBookName() != null) {
            wrapper.like("book_name", book.getBookName());
        }
        if (book.getAuthor() != null) {
            wrapper.like("author", book.getAuthor());
        }
        if (book.getIsbn() != null) {
            wrapper.like("isbn", book.getIsbn());
        }
        if (book.getPublishingHouse() != null) {
            wrapper.like("publishing_house", book.getPublishingHouse());
        }
        if (book.getCategory() != null) {
            wrapper.like("category", book.getCategory());
        }
        if (book.getYear() != null) {
            wrapper.eq("year", book.getYear());
        }
        if (book.getStatus() != null){
            wrapper.eq("status",book.getStatus());
        }
        // 使用构造好的查询条件执行查询，并返回查询结果
        return bookMapper.selectList(wrapper);
    }

    // 实现删除图书的功能
    @Override
    public void deleteBook(int bookId) {
        bookMapper.deleteById(bookId);
    }

}

