package com.example.library.controller;

import com.example.library.mapper.BookMapper;
import com.example.library.model.Admin;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import org.springframework.ui.Model;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/bookManage")
public class BookController {
    @Resource
    private BookMapper bookMapper;
    @Autowired
    private BookService bookService;

    // 检查管理员是否已登录，若未登录则重定向到登录页面
    @ModelAttribute
    public void checkAdminLogin(HttpSession session, HttpServletResponse response) throws IOException {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("/admin/login");
        }
    }

    // 显示图书管理主页面
    @GetMapping("")
    public String bookManage(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        return "bookManage";
    }

    // 显示添加图书表单页面
    @GetMapping("/addBook")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        return "addBook";
    }

    // 根据添加图书表单，提交表单，处理添加图书请求
    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book, Model model){
        // 验证图书添加表单，各字段均不能为空
        if (StringUtils.isBlank(book.getBookName())) {
            model.addAttribute("message", "图书名称不能为空！");
            return "addBook";
        }
        if (StringUtils.isBlank(book.getAuthor())) {
            model.addAttribute("message", "作者不能为空！");
            return "addBook";
        }
        if (StringUtils.isBlank(book.getIsbn())) {
            model.addAttribute("message", "ISBN不能为空！");
            return "addBook";
        }
        if (StringUtils.isBlank(book.getPublishingHouse())) {
            model.addAttribute("message", "出版社不能为空！");
            return "addBook";
        }
        if (StringUtils.isBlank(book.getCategory())) {
            model.addAttribute("message", "类别不能为空！");
            return "addBook";
        }
        if (book.getYear() == null) {
            model.addAttribute("message", "年份不能为空！");
            return "addBook";
        }
        book.setStatus(1);
        // 调用 bookService 添加图书并返回处理结果
        boolean isSuccess = bookService.addBook(book);
        if (isSuccess) {
            model.addAttribute("message", "添加成功！");
            model.addAttribute("book", new Book()); // 清空表单数据
        } else {
            model.addAttribute("message", "添加失败！");
        }
        return "addBook";
    }

    // 处理搜索图书请求
    @GetMapping("/searchBook")
    public String searchBooks(@ModelAttribute Book book, Model model) {
        List<Book> books = bookService.searchBooks(book);
        model.addAttribute("books", books);
        return "searchBook";
    }

    // 处理删除图书请求
    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam("bookId") int bookId, Model model) {
        bookService.deleteBook(bookId);
        return "redirect:/bookManage/searchBook";
    }
}
