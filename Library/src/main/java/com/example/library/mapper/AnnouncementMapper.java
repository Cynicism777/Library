package com.example.library.mapper;

import com.example.library.model.Announcement;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnouncementMapper {
    @Insert("INSERT INTO announcements(title, content, create_time, update_time) VALUES(#{title}, #{content}, #{createTime}, #{updateTime})")
    int insert(Announcement announcement);
}
