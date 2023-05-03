package com.example.library.controller;

import com.example.library.model.Announcement;
import com.example.library.model.Reader;
import com.example.library.service.GetAnnouncementService;
import com.example.library.service.ReaderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/reader/announcements") // 修改这里
public class GetAnnouncementController {

    @Autowired
    private GetAnnouncementService getAnnouncementService;

    @Autowired
    private ReaderLogService readerLogService;

    @GetMapping
    public String getAllAnnouncements(Model model, HttpSession session) {
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            return "redirect:/start";
        }

        List<Announcement> announcements = getAnnouncementService.getAllAnnouncements();
        model.addAttribute("announcements", announcements);
        return "announcements";
    }
}
