package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Borrow;
import com.example.library.service.BookService;
import com.example.library.service.BorrowService;
import com.example.library.service.ReaderLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.library.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reader")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private ReaderLogService readerLogService;

    @GetMapping("/readerLogin")
    public String loginPage() {
        return "readerLogin";
    }

    @PostMapping("/readerLogin")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) {
        Reader reader = readerLogService.validateReader(username, password);
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
            return "redirect:/start";
        }
        model.addAttribute("reader",reader);
        return "readerDashboard";
    }

    @GetMapping("/borrow")
    public String borrow(@ModelAttribute Book book, Model model, HttpSession session) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null){
            return "redirect:/start";
        }
        model.addAttribute("reader",reader);
        List<Book> books = borrowService.searchBooks(book);
        model.addAttribute("books", books);
        return "borrow";
    }

    @GetMapping("/borrow/{bookId}")
    public String borrowBook(@PathVariable("bookId") Integer bookId, HttpSession session, RedirectAttributes redirectAttributes) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "redirect:/start";
        }

        if (reader.getPenalty() == 0 && borrowService.borrowBook(bookId)) {
            Book book = borrowService.getById(bookId);
            Borrow borrow = new Borrow();
            borrow.setBookId(book.getBookId());
            borrow.setBookName(book.getBookName());
            borrow.setUserId(reader.getId());
            borrow.setUsername(reader.getUsername());
            LocalDateTime now = LocalDateTime.now();
            Date borrowTime = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
            borrow.setBorrowTime(borrowTime);

            borrowService.insertBorrowRecord(borrow);

            redirectAttributes.addFlashAttribute("message", "借阅成功！");
        } else {
            if (reader.getPenalty() == 0){
                redirectAttributes.addFlashAttribute("message", "借阅失败：该图书已借出！");
            }else {
                redirectAttributes.addFlashAttribute("message", "借阅失败：请缴纳罚款！");
            }
        }
        return "redirect:/reader/borrow";
    }

    @GetMapping("/returnBook")
    public String returnBook(Model model, HttpSession session) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "redirect:/start";
        }
        List<Borrow> borrowedBooks = borrowService.getBorrowedBooks(reader.getId());
        model.addAttribute("borrowedBooks", borrowedBooks);
        return "returnBook";
    }


    /*@PostMapping("/returnBook")
    public boolean returnBook(@RequestBody Borrow borrow) {
        return borrowService.returnBook(borrow);
    }*/

    /*@PostMapping(value = "/returnBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean returnBook(@RequestBody Borrow borrow) {
        return borrowService.returnBook(borrow);
    }*/

    @PostMapping(value = "/returnBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> returnBook(@RequestBody Borrow borrow) {
        try {
            boolean result = borrowService.returnBook(borrow);
            if (result) {
                return ResponseEntity.ok().body(result);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("An error occurred while returning the book.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while returning the book. Exception: " + e.getMessage());
        }
    }



    // 读者登出
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 从 session 中移除读者信息
        session.removeAttribute("reader");
        // 重定向到读者登录页面
        return "redirect:/start";
    }

}
