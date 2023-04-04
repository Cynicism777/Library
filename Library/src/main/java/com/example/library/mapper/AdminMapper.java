package com.example.library.mapper;

import com.example.library.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    @Select("SELECT * FROM administrator WHERE admin = #{admin} AND password = #{password}")
    Admin findByAdminAndPassword(@Param("admin") String admin, @Param("password") String password);

}
