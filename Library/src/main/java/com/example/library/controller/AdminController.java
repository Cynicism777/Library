package com.example.library.controller;

import com.example.library.model.Admin;
import com.example.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    // 显示管理员登录页面
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 验证管理员登录信息
    @PostMapping("/login")
    public String login(@RequestParam("admin") String admin, @RequestParam("password") String password, HttpSession session, Model model) {
        // 调用 AdminService 验证管理员登录信息
        Admin validAdmin = adminService.validateAdmin(admin, password);
        // 如果验证通过，将管理员信息存储在 session 中，然后重定向到图书管理页面
        if (validAdmin != null) {
            session.setAttribute("admin", validAdmin);
            return "redirect:/bookManage";
        } else {
            // 如果验证失败，返回错误信息并保持在登录页面
            model.addAttribute("message", "用户名或密码错误！");
            return "login";
        }
    }

    // 管理员登出
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 从 session 中移除管理员信息
        session.removeAttribute("admin");
        // 重定向到管理员登录页面
        return "redirect:/admin/login";
    }
}
