package com.example.library.controller;

import com.example.library.mapper.ReaderMapper;
import com.example.library.model.Admin;
import com.example.library.model.Reader;
import com.example.library.service.ReaderService;
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
@RequestMapping("/readerManage")
public class ReaderController {
    @Resource
    private ReaderMapper readerMapper;
    @Autowired
    private ReaderService readerService;

    // 检查管理员是否已登录，若未登录则重定向到登录页面
    @ModelAttribute
    public void checkAdminLogin(HttpSession session, HttpServletResponse response) throws IOException {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("/admin/login");
        }
    }

    // 显示读者管理主页面
    @GetMapping("")
    public String readerManage(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        return "readerManage";
    }

    // 显示添加读者表单页面
    @GetMapping("/addReader")
    public String addReaderForm(Model model) {
        model.addAttribute("reader", new Reader());
        return "addReader";
    }

    // 根据添加读者表单，提交表单，处理添加读者请求
    @PostMapping("/addReader")
    public String addReader(@ModelAttribute Reader reader, Model model) {
        if (StringUtils.isBlank(reader.getUsername())) {
            model.addAttribute("message", "读者用户名不能为空！");
            return "addReader";
        }
        if (StringUtils.isBlank(reader.getPassword())) {
            model.addAttribute("message", "密码不能为空！");
            return "addReader";
        }
//        if (readerService.isUsernameExists(reader.getUsername())) {
//            model.addAttribute("message", "用户名已存在！");
//            return "addReader";
//        }
        reader.setPenalty(0);
        boolean isSuccess = readerService.addReader(reader);
        if (isSuccess) {
            model.addAttribute("message", "添加成功！");
            model.addAttribute("reader", new Reader()); // 清空表单数据
        } else {
            model.addAttribute("message", "添加失败！");
        }
        return "addReader";
    }

    // 处理搜索读者请求
    @GetMapping("/searchReader")
    public String searchReaders(@ModelAttribute Reader reader, Model model) {
        List<Reader> readers = readerService.searchReaders(reader);
        model.addAttribute("readers", readers);
        return "searchReader";
    }

    // 处理删除读者请求
    @RequestMapping("/deleteReader")
    public String deleteReader(@RequestParam("readerId") int readerId, Model model) {
        readerService.deleteReader(readerId);
        return "redirect:/readerManage/searchReader";
    }
}
