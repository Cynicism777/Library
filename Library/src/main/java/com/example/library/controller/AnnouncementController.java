package com.example.library.controller;

import com.example.library.model.Admin;
import com.example.library.model.Announcement;
import com.example.library.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/createAnnouncement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    // 检查管理员是否已登录，若未登录则重定向到登录页面
    @ModelAttribute
    public void checkAdminLogin(HttpSession session, HttpServletResponse response) throws IOException {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("/start");
        }
    }

    // 显示添加公告表单页面
    @GetMapping("")
    public String createAnnouncementForm(Model model) {
        model.addAttribute("announcement", new Announcement());
        return "createannouncement";
    }

    // 处理添加公告表单提交的方法
    @PostMapping("")
    public String createAnnouncement(@ModelAttribute Announcement announcement, Model model) {
        LocalDateTime now = LocalDateTime.now(); // 获取当前时间
        announcement.setCreateTime(now); // 设置创建时间
        announcement.setUpdateTime(now); // 设置更新时间

        boolean isSuccess = announcementService.createAnnouncement(announcement);
        if (isSuccess) {
            model.addAttribute("message", "公告添加成功！");
            model.addAttribute("announcement", new Announcement()); // 清空表单数据
        } else {
            model.addAttribute("message", "公告添加失败！");
        }
        return "createannouncement";
    }
}
