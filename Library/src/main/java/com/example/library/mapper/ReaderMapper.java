package com.example.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.library.model.Admin;
import com.example.library.model.Reader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReaderMapper extends BaseMapper<Reader> {
    @Select("SELECT * FROM reader WHERE username = #{username}")
    Reader findReaderByUsername(String username);
    @Select("SELECT * FROM reader WHERE username = #{username} AND password = #{password}")
    Reader findByUsernameAndPassword(@Param("username") String admin, @Param("password") String password);
}
