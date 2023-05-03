package com.example.library.service.impl;

import com.example.library.mapper.GetAnnouncementMapper;
import com.example.library.model.Announcement;
import com.example.library.service.GetAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAnnouncementServiceImpl implements GetAnnouncementService {

    @Autowired
    private GetAnnouncementMapper getAnnouncementMapper;

    @Override
    public List<Announcement> getAllAnnouncements() {
        return getAnnouncementMapper.getAllAnnouncements();
    }
}
