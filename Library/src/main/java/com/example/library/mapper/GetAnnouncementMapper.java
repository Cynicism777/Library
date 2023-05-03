package com.example.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.library.model.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GetAnnouncementMapper extends BaseMapper<Announcement> {

    @Select("SELECT * FROM announcements")
    List<Announcement> getAllAnnouncements();
}
