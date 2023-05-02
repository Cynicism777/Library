package com.example.library.service.impl;

import com.example.library.mapper.AnnouncementMapper;
import com.example.library.model.Announcement;
import com.example.library.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public boolean createAnnouncement(Announcement announcement) {
        int rowsAffected = announcementMapper.insert(announcement);
        return rowsAffected > 0;
    }
}
