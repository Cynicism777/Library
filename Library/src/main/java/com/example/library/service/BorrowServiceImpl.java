package com.example.library.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.library.mapper.BorrowMapper;
import com.example.library.model.Book;
import com.example.library.mapper.BookMapper;
import com.example.library.model.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.updateById;

@Service
public class BorrowServiceImpl implements BorrowService{
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BorrowMapper borrowMapper;

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

    @Override
    public boolean insertBorrowRecord(Borrow borrow) {
        int result = borrowMapper.insertBorrowRecord(borrow);
        return result > 0;
    }

    @Override
    public List<Borrow> getBorrowedBooks(int userId) {
        return borrowMapper.getBorrowedBooksByUserId(userId);
    }

    @Override
    public boolean returnBook(Borrow borrow) {
        Date now = new Date();
        long days = (now.getTime() - borrow.getBorrowTime().getTime()) / (1000 * 60 * 60 * 24);
        int penalty = days > 3 ? (int)(days - 3) : 0;
        borrow.setReturnTime(now);
        borrow.setPenalty(penalty);
        int result = borrowMapper.updateReturnInfo(borrow);
        Book book = getById(borrow.getBookId());
        if (book != null) {
            book.setStatus(1);
            boolean bookUpdateResult = updateById(book);
            return result > 0 && bookUpdateResult;
        }
        return false;
    }

    @Override
    public List<Borrow> getAllBorrowRecords() {
        return borrowMapper.getAllBorrowRecords();
    }

}
