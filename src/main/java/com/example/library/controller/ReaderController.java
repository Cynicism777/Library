package com.example.library.controller;

import com.example.library.model.Reader;
import com.example.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/readerManage")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @GetMapping("")
    public String readerManage(Model model) {
        List<Reader> readers = readerService.findAll();
        model.addAttribute("readers", readers);
        return "readerManage";
    }

    // 添加读者页面
    @GetMapping("/addReader")
    public String  addReaderForm(Model model) {
        model.addAttribute("reader", new Reader());
        return "addReader";
    }

    // 处理添加读者表单
    @PostMapping("/addReader")
    public String addReader(@ModelAttribute Reader reader, Model model) {
        // 可添加表单验证逻辑
        readerService.insert(reader);
        return "redirect:/readerManage";
    }

    // 更新读者页面
    @GetMapping("/editReader/{id}")
    public String editReaderForm(@PathVariable("id") int id, Model model) {
        Reader reader = readerService.findById(id);
        model.addAttribute("reader", reader);
        return "editReader";
    }

    // 处理更新读者表单
    @PostMapping("/updateReader")
    public String updateReader(@ModelAttribute Reader reader, Model model) {
        // 可添加表单验证逻辑
        readerService.update(reader);
        return "redirect:/readerManage";
    }

    // 删除读者
    @GetMapping("/deleteReader/{id}")
    public String deleteReader(@PathVariable("id") int id) {
        readerService.delete(id);
        return "redirect:/readerManage";
    }



}
