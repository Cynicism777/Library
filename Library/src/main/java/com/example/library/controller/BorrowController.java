package com.example.library.controller;

import com.example.library.model.Book;
import org.springframework.ui.Model;
import com.example.library.model.Reader;
import com.example.library.service.BookService;
import com.example.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/reader")
public class BorrowController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService readerService;
    @GetMapping("/readerLogin")
    public String loginPage() {
        return "readerLogin";
    }
    @PostMapping("/readerLogin")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) {
        Reader reader = readerService.validateReader(username, password);
        if (reader != null) {
            session.setAttribute("reader", reader);
            return "redirect:/reader/readerDashboard";
        } else {
            model.addAttribute("message", "用户名或密码错误！");
            return "readerLogin";
        }
    }
    @GetMapping("/readerDashboard")
    public String readerDashboard(Model model, HttpSession session){
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null){
            return "redirect:/reader/readerLogin";
        }
        model.addAttribute("reader",reader);
        return "/readerDashboard";
    }

    @GetMapping("/borrow")
    public String borrow(Book book, Model model, HttpSession session) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null){
            return "redirect:/reader/readerLogin";
        }
        model.addAttribute("reader",reader);
        List<Book> books = bookService.searchBooks(book);
        model.addAttribute("books", books);
        return "borrow";
    }

    @PostMapping("/borrow/{bookId}")
    public String borrowBook(@PathVariable("bookId") Integer bookId, HttpSession session, RedirectAttributes redirectAttributes) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "redirect:/reader/readerLogin";
        }
        if (reader.getPenalty() == 0 && bookService.borrowBook(bookId)) {
            redirectAttributes.addFlashAttribute("message", "借阅成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "借阅失败");
        }
        return "redirect:/reader/borrow";
    }

    // 读者登出
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 从 session 中移除读者信息
        session.removeAttribute("reader");
        // 重定向到读者登录页面
        return "redirect:/reader/readerLogin";
    }

}
