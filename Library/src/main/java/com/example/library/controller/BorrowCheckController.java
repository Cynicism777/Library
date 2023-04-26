package com.example.library.controller;


import com.example.library.model.Admin;
import com.example.library.model.Borrow;
import com.example.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/borrowRecords")
public class BorrowCheckController {
    @Autowired
    private BorrowService borrowService;

    @ModelAttribute
    public void checkAdminLogin(HttpSession session, HttpServletResponse response) throws IOException {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("/start");
        }
    }

    @GetMapping("")
    public String borrowRecords(@RequestParam(required = false) String bookName,
                                @RequestParam(required = false) String username,
                                @RequestParam(required = false) Boolean completed,
                                Model model) {
        List<Borrow> borrowRecords = borrowService.getAllBorrowRecords();

        if (bookName != null && !bookName.isEmpty()) {
            borrowRecords = borrowRecords.stream()
                    .filter(b -> b.getBookName().toLowerCase().contains(bookName.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (username != null && !username.isEmpty()) {
            borrowRecords = borrowRecords.stream()
                    .filter(b -> b.getUsername().toLowerCase().contains(username.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (completed != null) {
            borrowRecords = borrowRecords.stream()
                    .filter(b -> (b.getReturnTime() != null) == completed)
                    .collect(Collectors.toList());
        }

        model.addAttribute("borrowRecords", borrowRecords);
        return "borrowRecords";
    }


}
